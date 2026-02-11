package com.rootcause.rootcauseanalysisagentservice.adapter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// Explicitly scan the base package so it finds the Controller, Manager, and Sanitizer
@SpringBootApplication(scanBasePackages = ["com.rootcause"])
class RootCauseAnalysisApplication

fun main(args: Array<String>) {
    runApplication<RootCauseAnalysisApplication>(*args)
}