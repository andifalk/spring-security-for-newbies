package com.example.security.demo

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.util.StringUtils
import java.util.stream.Collectors.toList

class JwtAuthoritiesConverter : Converter<Jwt, Collection<GrantedAuthority>> {

    override fun convert(jwt: Jwt): Collection<GrantedAuthority>? {
        return getAuthorities(jwt).stream()
            .map { "ROLE_${it.toUpperCase()}" }
            .map { SimpleGrantedAuthority(it) }
            .collect(
                toList()
            )
    }

    private fun getAuthorities(jwt: Jwt): Collection<String> {
        val authorities = jwt.getClaim<Any>("scope")
        return if (authorities is String) {
            if (StringUtils.hasText(authorities)) {
                listOf(*authorities.split(" ").toTypedArray())
            } else emptyList()
        } else emptyList()
    }
}