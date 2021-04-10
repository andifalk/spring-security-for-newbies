package com.example.security.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
internal class AdminRestController(private val messageService: MessageService) {

    @GetMapping
    fun admin() = AdminResponse(messageService.getAdminMessage())

    data class AdminResponse(var greeting: String)
}