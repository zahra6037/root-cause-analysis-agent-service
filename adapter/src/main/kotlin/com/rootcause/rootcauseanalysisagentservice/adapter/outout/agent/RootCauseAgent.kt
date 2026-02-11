package com.rootcause.rootcauseanalysisagentservice.adapter.output.ai.agent

import com.rootcause.domain.model.AgentAnalysisResponse
import dev.langchain4j.service.SystemMessage
import dev.langchain4j.service.UserMessage
import dev.langchain4j.service.V

/**
 * AI Agent interface for root cause analysis.
 * LangChain4j will implement this interface and handle LLM calls.
 */

interface RootCauseAgent {

    @SystemMessage("""
        You are an expert Site Reliability Engineer (SRE).
        Analyze the logs and return a structured report.
        
        CRITICAL: Respond ONLY with the JSON object. Do not include introductory text like "Sure, here is the analysis".
    """)
    @UserMessage("""
        Analyze this production incident:
        Category: {{suspectedCategory}}
        Context: {{context}}
        Logs: {{formattedLogs}}
    """)
    fun analyzeIncident(
        @V("suspectedCategory") suspectedCategory: String,
        @V("context") context: String,
        @V("logCount") logCount: Int,
        @V("formattedLogs") formattedLogs: String
    ): AgentAnalysisResponse
}