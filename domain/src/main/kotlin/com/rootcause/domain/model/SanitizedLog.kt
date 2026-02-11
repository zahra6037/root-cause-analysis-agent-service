package com.rootcause.domain.model


import java.time.Instant

data class SanitizedLog(
    val timestamp: Instant,
    val level: LogLevel,
    val component: String?,
    val errorType: String?,
    val message: String,
    val count: Int = 1
)