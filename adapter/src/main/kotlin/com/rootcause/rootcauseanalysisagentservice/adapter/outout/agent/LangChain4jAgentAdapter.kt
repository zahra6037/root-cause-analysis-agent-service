package com.rootcause.rootcauseanalysisagentservice.adapter.output.ai

import com.fasterxml.jackson.databind.ObjectMapper
import com.rootcause.domain.AIAgentPort
import com.rootcause.domain.model.*
import com.rootcause.rootcauseanalysisagentservice.adapter.output.ai.agent.RootCauseAgent
import org.springframework.stereotype.Component
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@Component
class LangChain4jAgentAdapter(
    private val rootCauseAgent: RootCauseAgent,
    private val objectMapper: ObjectMapper
) : AIAgentPort {

    override suspend fun analyzeRootCause(
        logs: List<SanitizedLog>,
        suspectedCategory: IncidentCategory,
        context: String?
    ): AIAnalysisResult = withContext(Dispatchers.IO) {

        val formattedLogs = formatLogsForAgent(logs)

        // Call the AI agent (blocking call, so we use Dispatchers.IO)
        val response = rootCauseAgent.analyzeIncident(
            suspectedCategory = suspectedCategory.name,
            context = context ?: "No additional context provided",
            logCount = logs.size,
            formattedLogs = formattedLogs
        )

        // MAP the response object directly to AIAnalysisResult
        AIAnalysisResult(
            rootCause = response.rootCause,
            reasoning = response.reasoning,
            affectedComponents = response.affectedComponents.toSet(),
            suggestedFixes = response.suggestedFixes.map {
               SuggestedFix(
                    description = it.description,
                    // Convert to UPPERCASE to match the Enum, and provide a fallback
                    priority = try {
                        Priority.valueOf(it.priority.uppercase().trim())
                    } catch (e: Exception) {
                        Priority.MEDIUM // Default to MEDIUM if the AI returns something weird
                    },
                    estimatedImpact = it.estimatedImpact
                )
            },
            confidence = response.confidence,
            tokensUsed = 0,
            modelUsed = "ollama-mistral"
        )

    }

    private fun formatLogsForAgent(logs: List<SanitizedLog>): String {
        // Limit to 50 logs to avoid token limits
        val logsToShow = logs.take(50)

        return logsToShow.joinToString("\n") { log ->
            "[${log.timestamp}] [${log.level}] [${log.component}] ${log.message}" +
                    log.errorType?.let { " (Error: $it)" }.orEmpty()
        } + if (logs.size > 50) {
            "\n... (${logs.size - 50} more logs omitted)"
        } else ""
    }
}