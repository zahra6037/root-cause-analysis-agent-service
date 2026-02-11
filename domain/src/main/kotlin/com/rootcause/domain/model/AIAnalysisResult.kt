package com.rootcause.domain.model

data class AIAnalysisResult(
    val rootCause: String,
    val reasoning: String,                    // Explanation of how AI arrived at conclusion
    val affectedComponents: Set<String>,
    val suggestedFixes: List<SuggestedFix>,
    val confidence: Double,                   // 0.0 to 1.0
    val tokensUsed: Int,
    val modelUsed: String
)
