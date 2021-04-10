package com.example.security.demo

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest
import org.springframework.boot.actuate.health.HealthEndpoint
import org.springframework.boot.actuate.info.InfoEndpoint
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.web.servlet.invoke
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager

@EnableWebSecurity
class WebSecurityConfiguration {

    @Bean
    fun passwordEncoder(): PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    @Bean
    fun userDetailsService(): UserDetailsService {
        val manager = InMemoryUserDetailsManager()
        manager.createUser(
            User.withUsername("user")
                .password(passwordEncoder().encode("secret"))
                .roles("USER").build())
        manager.createUser(
            User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("USER", "ADMIN").build()
        )
        return manager
    }

    @Configuration
    @Order(1)
    class ApiSecurityConfiguration : WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {
            http {
                securityMatcher("/api/**")
                authorizeRequests {
                    authorize(anyRequest, authenticated)
                }
                httpBasic { }
                formLogin { }
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
                    authorize(anyRequest, authenticated)
                }
                httpBasic { }
                formLogin { }
            }
        }
    }
}