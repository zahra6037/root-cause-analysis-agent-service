package com.rootcause.domain.model

data class SuggestedFixResponse(
    val description: String,
    val priority: String,
    val estimatedImpact: String
)
