package com.rootcause.domain.model

data class PreAnalysisContext(
    val suspectedCategory: IncidentCategory,
    val servicesInvolved: Set<String>
)

