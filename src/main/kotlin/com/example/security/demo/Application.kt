package com.example.security.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    val app = SpringApplication(Application::class.java)
    app.applicationStartup = BufferingApplicationStartup(2048)
    app.run(*args)
}
