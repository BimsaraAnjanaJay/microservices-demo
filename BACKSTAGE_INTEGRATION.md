# Backstage Integration Guide

This repository now includes comprehensive Backstage catalog definitions for all microservices in the Online Boutique demo application.

## 📋 What's Included

### **Service Catalog Files:**
- ✅ **12 Microservices** - Each with its own `catalog-info.yaml`
- ✅ **System Definition** - Overall system architecture
- ✅ **Infrastructure Components** - OpenTelemetry Collector, Jaeger, Redis
- ✅ **Service Dependencies** - Showing relationships between services
- ✅ **Direct Links to Traces** - Links to Jaeger UI for each service

### **Organized by Teams:**
- **team-platform**: Infrastructure and shared services
- **team-commerce**: Core business logic services  
- **team-frontend**: User-facing applications
- **team-ai**: AI/ML services

## 🚀 How to Import into Backstage

### **Option 1: Import Individual Services**
```bash
# In your Backstage instance
yarn backstage-cli catalog:import ./src/adservice/catalog-info.yaml
yarn backstage-cli catalog:import ./src/cartservice/catalog-info.yaml
# ... repeat for all services
```

### **Option 2: Import All at Once**
```bash
# Import the main catalog location
yarn backstage-cli catalog:import ./backstage-catalog.yaml
```

### **Option 3: Add to Backstage Config**
Add to your `app-config.yaml`:
```yaml
catalog:
  locations:
    - type: file
      target: /path/to/microservices-demo/backstage-catalog.yaml
```

## 🔍 Features in Backstage

### **Service Catalog View:**
- **Service Overview** - Description, tags, ownership
- **Dependencies** - Visual graph of service relationships
- **Links** - Direct access to:
  - 🔗 Live application (frontend)
  - 📊 Jaeger traces for each service
  - 📈 Observability dashboards

### **System Architecture:**
- **online-boutique** system showing all components
- **Service ownership** by different teams
- **Technology stack** visibility (Java, Go, Python, C#, Node.js)

### **OpenTelemetry Integration:**
- **Direct trace links** for each service
- **Observability metadata** in service definitions
- **Dependency mapping** for trace correlation

## 🔧 Advanced Integration

### **Adding OpenTelemetry Plugin to Backstage:**

1. **Install the plugin:**
```bash
yarn add @backstage/plugin-catalog-backend-module-opentelemetry
```

2. **Configure in your backend:**
```typescript
// packages/backend/src/index.ts
import { createBackend } from '@backstage/backend-defaults';
import { catalogModuleOpenTelemetry } from '@backstage/plugin-catalog-backend-module-opentelemetry';

const backend = createBackend();
backend.add(catalogModuleOpenTelemetry());
```

### **Jaeger UI Integration:**

Add iframe component to show Jaeger traces directly in Backstage:

```typescript
// In your Backstage app
const JaegerComponent = () => (
  <iframe 
    src="http://localhost:16686"
    width="100%" 
    height="600px"
    title="Jaeger Traces"
  />
);
```

## 📊 What You'll See in Backstage

### **Service Catalog:**
- 📦 **12 microservices** with full metadata
- 🏗️ **1 system** (online-boutique)
- 🔧 **3 infrastructure components**
- 👥 **4 teams** with service ownership

### **Service Details:**
- **API documentation** links
- **Source code** repository links  
- **Live application** access
- **Distributed traces** for debugging
- **Service dependencies** visualization

### **Observability Integration:**
- **Direct Jaeger links** for each service
- **Trace-to-code** correlation
- **Performance monitoring** metadata
- **SLA/SLO** tracking capabilities

## 🎯 Benefits

1. **Service Discovery** - Easy to find and understand services
2. **Ownership Clarity** - Know who owns what
3. **Dependency Mapping** - Understand service relationships
4. **Observability Links** - Quick access to traces and metrics
5. **Documentation Hub** - Centralized service information
6. **Developer Experience** - Streamlined debugging and development

Your microservices are now fully integrated with Backstage! 🎉
