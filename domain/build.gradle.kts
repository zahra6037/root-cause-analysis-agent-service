//plugins {
//    kotlin("jvm")
//}
//
//dependencies {
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
//
//    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
//    testImplementation("io.mockk:mockk:1.13.8")
//    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
//}

plugins {
    kotlin("jvm")
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
    // No Spring dependencies here
}
