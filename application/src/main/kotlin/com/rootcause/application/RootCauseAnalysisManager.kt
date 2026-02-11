package com.rootcause.application

import LogSanitizer
import com.rootcause.domain.AIAgentPort
import com.rootcause.domain.model.AIAnalysisResult
import com.rootcause.domain.model.IncidentCategory
import org.springframework.stereotype.Service

@Service
class RootCauseAnalysisManager(
    private val sanitizer: LogSanitizer,
    private val aiAgent: AIAgentPort
) {
    suspend fun analyze(
        rawLogs: List<String>,
        suspectedCategory: IncidentCategory,
        context: String?
    ): AIAnalysisResult {
        // 1. Sanitize the incoming raw strings into structured domain logs
        val sanitizedLogs = sanitizer.sanitize(rawLogs)

        // 2. Delegate to the AI adapter for heavy lifting
        return aiAgent.analyzeRootCause(
            logs = sanitizedLogs,
            suspectedCategory = suspectedCategory,
            context = context
        )
    }
}