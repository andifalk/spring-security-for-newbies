package com.example.security.demo

import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
internal class AdminRestController(private val messageService: MessageService) {

    @GetMapping
    fun admin(@AuthenticationPrincipal user: User) =
        AdminResponse(messageService.getAdminMessage(), user.username)

    data class AdminResponse(val greeting: String, val username: String)
}