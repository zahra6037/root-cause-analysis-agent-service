# Root Cause Analysis (RCA) Agent Service

An intelligent Site Reliability Engineering (SRE) assistant built with **Spring Boot**, **Kotlin Coroutines**, and **LangChain4j**. This service automates production incident investigations by providing an LLM agent with specialized tools to analyze logs and suggest remediations.

## 🧠 System Architecture

This project follows a **Hexagonal Architecture** (Ports & Adapters) pattern.

### Core Modules
* **`:domain`**: Pure business logic. Defines the `AIAgentPort`, `SanitizedLog`, and `AIAnalysisResult`.
* **`:application`**: Use case orchestration (connecting the web entry points to the AI logic).
* **`:adapter`**: Infrastructure layer containing:
    * **`LangChain4jAgentAdapter`**: Orchestrates the AI flow and parses structured JSON responses.
    * **`LogAnalysisTools`**: Specialized Kotlin tools (`@Tool`) the AI can call to filter, group, and cluster logs.
    * **`RootCauseAgent`**: Declarative AI interface defining the SRE persona and prompt strategy.

## 🤖 AI Agent Capabilities
The agent is more than a simple prompt; it is an **active investigator** that can:
1. **Cluster Errors**: Detect cascading failures by identifying errors occurring within narrow time windows (30s).
2. **Calculate Impact**: Automatically determine error rate percentages to assess urgency.
3. **Map Remediations**: Correlate incident categories (like `RESOURCE_EXHAUSTION`) with established SRE playbooks.

## 🛠 Tech Stack
- **Language**: Kotlin 1.9 (with Coroutines)
- **Framework**: Spring Boot 3.4.1
- **AI Orchestration**: LangChain4j 0.36.2
- **Local LLM**: Ollama (Mistral)
- **Build Tool**: Gradle (Kotlin DSL)

## 🚦 Getting Started

### Prerequisites
1. **Ollama**: Download and install from [ollama.com](https://ollama.com/).
2. **LLM Model**: This service defaults to the `mistral` model. Download it by running:
   ```bash
   ollama pull mistral

## 🛠 Local Setup

This project uses a strict configuration approach. You **must** create a local configuration file that is ignored by Git to provide your environment-specific settings.

1. Create a local properties file:
   `adapter/src/main/resources/application-local.yml`

2. Add your local configuration:
```yaml
ai:
  base-url: ${AI_BASE_URL}
  model: ${AI_MODEL:mistral}
  temperature: ${AI_TEMP:0.2: 0.2}
  timeout-seconds: {$TIME: 60}
  ```
### 🚀 Running the Application
1. Go to Run > Edit Configurations.
2. Select your RootCauseAnalysisApplication.
3. In the VM options field, paste:
   4. -Dspring.profiles.active=local

### 🧪 Testing the API
```
curl --location 'http://localhost:8080/api/v1/analysis/trigger' \
--header 'Content-Type: application/json' \
--data '{
"rawLogs": [
"2026-02-06 14:00:01 INFO [order-service] Processing order #123",
"2026-02-06 14:00:05 ERROR [order-service] Connection timeout calling inventory-api",
"2026-02-06 14:00:05 WARN [order-service] Retrying connection (1/3)"
],
"category": "TIMEOUTS",
"context": "Customer reported checkout is hanging"
}
```

### ❗Troubleshooting


| Error | Cause | Solution |
|---|---|---|
| 404: model 'mistral' not found | The model hasn't been downloaded to Ollama. | Run ollama pull mistral in your terminal. |
| Connection Refused | Ollama is not running. | Ensure the Ollama app is open and running in your taskbar. |
| UnsatisfiedDependencyException | Row 2, Col 2 | Row 2, Col 3 |

