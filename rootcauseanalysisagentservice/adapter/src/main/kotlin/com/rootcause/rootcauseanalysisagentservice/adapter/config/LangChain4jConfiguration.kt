package com.rootcause.rootcauseanalysisagentservice.adapter.config

import com.rootcause.rootcauseanalysisagentservice.adapter.output.ai.agent.RootCauseAgent
import com.rootcause.rootcauseanalysisagentservice.adapter.output.ai.tools.LogAnalysisTools
import dev.langchain4j.memory.chat.MessageWindowChatMemory
import dev.langchain4j.model.chat.ChatLanguageModel
import dev.langchain4j.service.AiServices
import dev.langchain4j.model.ollama.OllamaChatModel
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@EnableConfigurationProperties(AIAgentConfiguration::class)
@Configuration
class LangChain4jConfiguration {
    @Bean
    fun ollamaChatModel(
        config: AIAgentConfiguration
    ): ChatLanguageModel {
        return OllamaChatModel.builder()
            .baseUrl(config.baseUrl)
            .modelName(config.model)
            .temperature(config.temperature)
            .timeout(Duration.ofSeconds(config.timeoutSeconds))
            .build()
    }

    @Bean
    fun rootCauseAgent(
        chatLanguageModel: ChatLanguageModel,
        logAnalysisTools: LogAnalysisTools
    ): RootCauseAgent {
        return AiServices.builder(RootCauseAgent::class.java)
            .chatLanguageModel(chatLanguageModel)
            .tools(logAnalysisTools)
            .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
            .build()
    }
}

