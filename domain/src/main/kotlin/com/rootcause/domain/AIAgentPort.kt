package com.rootcause.domain

import com.rootcause.domain.model.AIAnalysisResult
import com.rootcause.domain.model.IncidentCategory
import com.rootcause.domain.model.SanitizedLog

interface AIAgentPort {
    /**
     * Analyzes sanitized logs to determine root cause.
     * This is the expensive operation we want to minimize.
     */
    suspend fun analyzeRootCause(
        logs: List<SanitizedLog>,
        suspectedCategory: IncidentCategory,
        context: String?
    ): AIAnalysisResult
}