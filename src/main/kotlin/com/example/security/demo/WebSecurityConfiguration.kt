package com.example.security.demo

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.boot.actuate.health.HealthEndpoint
import org.springframework.boot.actuate.info.InfoEndpoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfiguration {

    @Bean
    fun jwtAuthenticationConverter(): JwtAuthenticationConverter {
        val jwtAuthoritiesConverter = JwtAuthoritiesConverter()
        val jwtAuthenticationConverter = JwtAuthenticationConverter()
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtAuthoritiesConverter)
        return jwtAuthenticationConverter
    }

    @Configuration
    @Order(1)
    class ApiSecurityConfiguration : WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {
            http {
                securityMatcher("/api/**")
                authorizeRequests {
                    authorize("/api/admin", hasRole("ADMIN"))
                    authorize(anyRequest, hasAnyRole("USER", "ADMIN"))
                }
                oauth2ResourceServer { jwt { } }
                csrf { disable() }
                sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
                httpBasic { disable() }
            }
        }

        override fun configure(web: WebSecurity) {
            web.ignoring().mvcMatchers("/v3/**", "/swagger-ui.html", "/swagger-ui/**")
        }
    }

    @Configuration
    @Order(2)
    class ActuatorSecurityConfiguration : WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {
            http {
                authorizeRequests {
                    authorize(EndpointRequest.to(HealthEndpoint::class.java), permitAll)
                    authorize(EndpointRequest.to(InfoEndpoint::class.java), permitAll)
                    authorize(EndpointRequest.toAnyEndpoint(), hasRole("ADMIN"))
                    authorize(anyRequest, authenticated)
                }
                oauth2ResourceServer { jwt { } }
                csrf { disable() }
                sessionManagement { sessionCreationPolicy = SessionCreationPolicy.STATELESS }
                httpBasic { disable() }
            }
        }
    }
}