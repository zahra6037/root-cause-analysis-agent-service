package com.rootcause.adapter.dto

data class AnalysisRequestDto(
    val rawLogs: List<String>,
    val category: String,
    val context: String?
)