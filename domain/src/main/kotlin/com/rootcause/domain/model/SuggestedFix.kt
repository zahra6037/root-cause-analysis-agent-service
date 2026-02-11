package com.rootcause.domain.model

data class SuggestedFix(
    val description: String,
    val priority: Priority,
    val estimatedImpact: String
)
enum class Priority {
    CRITICAL,
    HIGH,
    MEDIUM,
    LOW
}
