//pluginManagement {
//	repositories {
//		maven { url = uri("https://repo.spring.io/snapshot") }
//		gradlePluginPortal()
//	}
//}
//rootProject.name = "rootcauseanalysisagentservice"

rootProject.name = "root-cause-analysis-agent-service"

include(
	"domain",
	"application",
	"adapter"
)