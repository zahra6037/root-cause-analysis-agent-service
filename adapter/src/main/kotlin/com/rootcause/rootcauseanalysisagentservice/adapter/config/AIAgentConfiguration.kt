package com.rootcause.rootcauseanalysisagentservice.adapter.config

import org.springframework.boot.context.properties.ConfigurationProperties

//Do NOT push the LLM configuration as "Hardcoded", let use confiug file
@ConfigurationProperties(prefix = "ai")
data class AIAgentConfiguration(
    val baseUrl: String,
    val model: String,
    val temperature: Double,
    val timeoutSeconds: Long
)