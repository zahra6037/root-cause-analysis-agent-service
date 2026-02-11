package com.rootcause.domain.model

/**
 * These define the schema the AI is expected to follow.
 * Moving them to domain ensures the business logic knows how
 * to interpret AI output regardless of the framework.
 */
data class AgentAnalysisResponse(
    val rootCause: String,
    val reasoning: String,
    val affectedComponents: List<String>,
    val suggestedFixes: List<SuggestedFixResponse>,
    val confidence: Double
)
