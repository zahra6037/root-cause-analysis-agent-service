package com.rootcause.application

import LogSanitizer
import com.rootcause.domain.model.LogLevel
import com.rootcause.domain.model.SanitizedLog
import org.springframework.stereotype.Component
import java.time.Instant

@Component // This tells Spring: "I am the bean you are looking for!"
class DefaultLogSanitizer : LogSanitizer {

    override suspend fun sanitize(rawLogs: List<String>): List<SanitizedLog> {
        return rawLogs.map { line ->
            // For now, we wrap the raw string.
            // Later, you can add Regex logic here to parse levels and timestamps.
            SanitizedLog(
                timestamp = Instant.now(),
                level = detectLevel(line),
                component = "system-log",
                errorType = null,
                message = line
            )
        }
    }

    private fun detectLevel(line: String): LogLevel {
        return when {
            line.contains("ERROR", ignoreCase = true) -> LogLevel.ERROR
            line.contains("WARN", ignoreCase = true) -> LogLevel.WARN
            line.contains("FATAL", ignoreCase = true) -> LogLevel.FATAL
            else -> LogLevel.INFO
        }
    }
}