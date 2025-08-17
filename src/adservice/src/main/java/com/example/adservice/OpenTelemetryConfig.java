package com.example.adservice;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;

public class OpenTelemetryConfig {
    private static final String OTLP_ENDPOINT = "http://otel-collector:4317";

    public static void init() {
        OtlpGrpcSpanExporter exporter = OtlpGrpcSpanExporter.builder()
                .setEndpoint(OTLP_ENDPOINT)
                .build();

        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
                .addSpanProcessor(BatchSpanProcessor.builder(exporter).build())
                .build();

        OpenTelemetrySdk openTelemetry = OpenTelemetrySdk.builder()
                .setTracerProvider(tracerProvider)
                .build();

        GlobalOpenTelemetry.set(openTelemetry);
    }

    public static Tracer tracer() {
        return GlobalOpenTelemetry.getTracer("adservice");
    }
}
