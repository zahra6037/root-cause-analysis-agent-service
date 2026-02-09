package com.rootcause.domain.model

data class RootCauseAnalysisRequest(
    val logs: List<SanitizedLog>,
    val context: String? = null
)
