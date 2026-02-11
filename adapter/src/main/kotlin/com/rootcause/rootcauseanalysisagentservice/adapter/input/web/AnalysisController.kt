package com.rootcause.adapter.input.web

import com.rootcause.application.RootCauseAnalysisManager
import com.rootcause.domain.model.AIAnalysisResult
import com.rootcause.domain.model.IncidentCategory
import com.rootcause.rootcauseanalysisagentservice.adapter.dto.AnalysisRequestDto
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/analysis")
class AnalysisController(
    private val manager: RootCauseAnalysisManager
) {

    @PostMapping("/trigger")
    suspend fun triggerAnalysis(@RequestBody request: AnalysisRequestDto): AIAnalysisResult {
        val category = try {
            IncidentCategory.valueOf(request.category.uppercase())
        } catch (e: Exception) {
            IncidentCategory.UNKNOWN
        }

        return manager.analyze(
            rawLogs = request.rawLogs,
            suspectedCategory = category,
            context = request.context
        )
    }
}