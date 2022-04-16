package com.example.security.demo

import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

@Component
class DemoAuthenticationProvider : AuthenticationProvider {

    private val log = LoggerFactory.getLogger(DemoAuthenticationProvider::class.java)

    override fun authenticate(authentication: Authentication?): Authentication {
        log.info("Authenticating")
        val myAuthentication = MyAuthentication()
        myAuthentication.isAuthenticated = false
        return myAuthentication
    }

    override fun supports(authentication: Class<*>?): Boolean = true
}

class MyAuthentication : AbstractAuthenticationToken(AuthorityUtils.createAuthorityList("USER")) {

    override fun getCredentials(): Any {
        return "n/a"
    }

    override fun getPrincipal(): Any {
        return User.withUsername("user")
            .password("")
            .roles("USER").build()
    }
}