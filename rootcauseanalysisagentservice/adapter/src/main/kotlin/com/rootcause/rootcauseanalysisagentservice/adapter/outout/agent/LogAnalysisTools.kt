package com.rootcause.rootcauseanalysisagentservice.adapter.output.ai.tools

import com.rootcause.domain.model.IncidentCategory
import com.rootcause.domain.model.LogLevel
import com.rootcause.domain.model.SanitizedLog
import dev.langchain4j.agent.tool.Tool
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant

/**
 * Tools that AI agents can use to analyze logs.
 * Each method annotated with @Tool becomes available to the agent.
 */
@Component
class LogAnalysisTools {

    @Tool("Filter logs by minimum severity level (ERROR, WARN, INFO, etc)")
    fun filterLogsBySeverity(
        logs: List<SanitizedLog>,
        minLevel: String
    ): List<SanitizedLog> {
        val level = LogLevel.valueOf(minLevel.uppercase())
        return logs.filter { it.level >= level }
    }

    @Tool("Group logs by component/service name") //change it back to String & not String?
    fun groupLogsByComponent(logs: List<SanitizedLog>): Map<String?, Int> {
        return logs.groupingBy { it.component }.eachCount()
    }

    @Tool("Extract and count error types from logs")
    fun extractErrorTypes(logs: List<SanitizedLog>): Map<String, Int> {
        return logs
            .mapNotNull { it.errorType }
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedByDescending { it.second }
            .toMap()
    }

    @Tool("Find logs that occurred within a time window (in seconds)")
    fun findTemporallyCloseErrors(
        logs: List<SanitizedLog>,
        maxTimeDeltaSeconds: Long = 30
    ): List<String> {
        val sorted = logs.sortedBy { it.timestamp }
        val clusters = mutableListOf<String>()

        for (i in sorted.indices) {
            val closeErrors = mutableListOf<SanitizedLog>()
            closeErrors.add(sorted[i])

            for (j in i + 1 until sorted.size) {
                val timeDiff = Duration.between(sorted[i].timestamp, sorted[j].timestamp).seconds
                if (timeDiff <= maxTimeDeltaSeconds) {
                    closeErrors.add(sorted[j])
                } else {
                    break
                }
            }

            if (closeErrors.size > 1) {
                clusters.add(
                    "Cluster at ${sorted[i].timestamp}: ${closeErrors.size} errors within ${maxTimeDeltaSeconds}s - " +
                            "Components: ${closeErrors.map { it.component }.distinct().joinToString()}"
                )
            }
        }

        return clusters
    }

    @Tool("Calculate error rate percentage over time")
    fun calculateErrorRate(logs: List<SanitizedLog>): Double {
        if (logs.isEmpty()) return 0.0
        val errorCount = logs.count {
            it.level == LogLevel.ERROR || it.level == LogLevel.FATAL
        }
        return (errorCount.toDouble() / logs.size) * 100
    }

    @Tool("Get timeline of events showing when errors started and their progression")
    fun getEventTimeline(logs: List<SanitizedLog>): List<String> {
        return logs
            .sortedBy { it.timestamp }
            .map { "${it.timestamp} - [${it.level}] [${it.component}] ${it.message.take(100)}" }
            .take(20) // Limit to first 20 for readability
    }

    @Tool("Get known remediation steps for a specific incident category")
    fun getKnownRemediations(category: String): List<String> {
        val cat = try {
            IncidentCategory.valueOf(category.uppercase())
        } catch (e: IllegalArgumentException) {
            return listOf("Unknown category: $category")
        }

        return when (cat) {
            IncidentCategory.DEPENDENCY_FAILURE -> listOf(
                "Check network connectivity to dependent services",
                "Verify service health endpoints are responding",
                "Review circuit breaker configurations",
                "Check DNS resolution and service discovery"
            )
            IncidentCategory.RESOURCE_EXHAUSTION -> listOf(
                "Scale up resources (CPU/Memory) immediately",
                "Review resource limits and quotas",
                "Check for memory leaks in application code",
                "Analyze garbage collection logs"
            )
            IncidentCategory.TIMEOUTS -> listOf(
                "Increase timeout configurations",
                "Optimize slow database queries or API calls",
                "Check connection pool settings",
                "Review async operation timeout values"
            )
            IncidentCategory.CONFIGURATION_ERROR -> listOf(
                "Validate configuration files against schema",
                "Check environment variables are set correctly",
                "Review recent configuration changes",
                "Verify feature flag settings"
            )
            IncidentCategory.AUTHENTICATION_AUTHORIZATION -> listOf(
                "Verify API keys and credentials are valid",
                "Check token expiration times",
                "Review permission configurations",
                "Validate OAuth/JWT settings"
            )
            IncidentCategory.DEPLOYMENT_REGRESSION -> listOf(
                "Rollback to previous stable deployment",
                "Compare code changes in latest release",
                "Review deployment logs for errors",
                "Check for version compatibility issues"
            )
            IncidentCategory.DATA_INCONSISTENCY -> listOf(
                "Validate data integrity across systems",
                "Check for schema mismatches",
                "Review recent data migrations",
                "Verify data synchronization processes"
            )
            IncidentCategory.UNKNOWN -> listOf(
                "Conduct manual investigation",
                "Review full log history and metrics",
                "Check system dashboards for anomalies",
                "Escalate to on-call engineer"
            )
        }
    }
}