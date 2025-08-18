package main

import (
    "context"
    "log"

    "go.opentelemetry.io/otel"
    "go.opentelemetry.io/otel/sdk/trace"
    "go.opentelemetry.io/otel/exporters/otlp/otlptrace/otlptracegrpc"
    "google.golang.org/grpc"
)

func InitTracer() func() {
    conn, err := grpc.Dial("otel-collector:4317", grpc.WithInsecure())
    if err != nil {
        log.Fatal(err)
    }

    exporter, err := otlptracegrpc.New(context.Background(), otlptracegrpc.WithGRPCConn(conn))
    if err != nil {
        log.Fatal(err)
    }

    tp := trace.NewTracerProvider(
        trace.WithBatcher(exporter),
    )
    otel.SetTracerProvider(tp)

    return func() {
        _ = tp.Shutdown(context.Background())
        conn.Close()
    }
}
