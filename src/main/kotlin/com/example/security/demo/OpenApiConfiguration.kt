package com.example.security.demo

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.tags.Tag
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfiguration {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI().components(
            Components()
                .addSecuritySchemes(
                    "basicAuth", SecurityScheme()
                        .type(SecurityScheme.Type.HTTP).scheme("basic")
                )
        ).info(
            Info().title("Custom API")
                .version("100")
        ).addTagsItem(Tag().name("Message API").name("Administrator API"))
    }
}