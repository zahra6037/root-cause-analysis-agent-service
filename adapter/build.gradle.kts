plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    kotlin("jvm")
    kotlin("plugin.spring")
    ///java
}

//java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(21))
//    }
//}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":application"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.jetbrains.kotlin:kotlin-reflect")


    // LangChain4j - AI Agent Framework
    implementation("dev.langchain4j:langchain4j:0.36.2")
    implementation("dev.langchain4j:langchain4j-ollama:0.36.2")

//    implementation("dev.langchain4j:langchain4j-open-ai:0.36.2")
//    implementation("dev.langchain4j:langchain4j-anthropic:0.36.2")

    // Spring Boot integration for LangChain4j
    implementation("dev.langchain4j:langchain4j-spring-boot-starter:0.36.2")

    // JSON processing
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Coroutines (should already be there)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    //Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

//plugins {
//    id("org.springframework.boot")
//    id("io.spring.dependency-management")
//    kotlin("jvm")
//    kotlin("plugin.spring")
//    java
//}
//
//java {
//    toolchain {
//        languageVersion.set(JavaLanguageVersion.of(21))
//    }
//}
//
//kotlin {
//    jvmToolchain(21)
//}
//
//dependencies {
//    implementation(project(":domain"))
//    implementation(project(":application"))
//
//    // Spring Boot
//    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("org.springframework.boot:spring-boot-starter-actuator")
//    implementation("org.springframework.boot:spring-boot-starter-webflux")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//
//    // Coroutines - needed for suspend functions
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
//
//    // LangChain4j - AI Agent Framework
//    implementation("dev.langchain4j:langchain4j:0.36.2")
//    implementation("dev.langchain4j:langchain4j-open-ai:0.36.2")
//    implementation("dev.langchain4j:langchain4j-anthropic:0.36.2")
//
//    // Spring Boot integration for LangChain4j
//    implementation("dev.langchain4j:langchain4j-spring-boot-starter:0.36.2")
//
//    // JSON processing
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//
//    // Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
//
//    // Testing
//    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("io.mockk:mockk:1.13.8")
//}
//
//tasks.bootJar {
//    enabled = true
//}
//
//tasks.jar {
//    enabled = false
//}