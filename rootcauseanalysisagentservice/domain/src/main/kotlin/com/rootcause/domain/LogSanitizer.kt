import com.rootcause.domain.model.SanitizedLog

interface LogSanitizer {
   suspend fun sanitize(rawLogs: List<String>): List<SanitizedLog>
}