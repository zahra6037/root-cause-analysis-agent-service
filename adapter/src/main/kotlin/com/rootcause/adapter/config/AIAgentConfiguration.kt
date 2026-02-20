package com.rootcause.adapter.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "ai")
data class AIAgentConfiguration(
    val baseUrl: String,
    val model: String,
    val temperature: Double,
    val timeoutSeconds: Long
)