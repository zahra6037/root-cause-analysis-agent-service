//plugins {
//    kotlin("jvm")
//}
//
//dependencies {
//    implementation(project(":domain"))
//
//    implementation("org.springframework:spring-context:6.1.3")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
//
//    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.1")
//    testImplementation("io.mockk:mockk:1.13.8")
//    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
//}

plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation(project(":domain"))
    implementation("org.springframework:spring-context:6.1.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
}
