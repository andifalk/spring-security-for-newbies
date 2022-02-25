package com.example.security.demo

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
internal class AdminRestController(private val messageService: MessageService) {

    @Operation(summary = "Get message from administrator", tags = ["Administrator API"])
    @ResponseStatus(OK)
    @GetMapping
    fun admin() = AdminResponse(messageService.getAdminMessage())

    data class AdminResponse(var greeting: String)
}