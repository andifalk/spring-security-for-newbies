package com.example.security.demo

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.security.SecuritySchemes
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup

@OpenAPIDefinition(security = [SecurityRequirement(name = "basicAuth")])
@SecuritySchemes(
    SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
    )
)
@SpringBootApplication
class Application

fun main(args: Array<String>) {
    val app = SpringApplication(Application::class.java)
    app.applicationStartup = BufferingApplicationStartup(2048)
    app.run(*args)
}
