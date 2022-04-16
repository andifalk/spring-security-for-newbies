package com.example.security.demo

import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus.OK
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/message")
internal class MessageRestController(private val messageService: MessageService) {

    @Operation(summary = "Get message", tags = ["Message API"])
    @ResponseStatus(OK)
    @GetMapping
    fun helloMessage(@AuthenticationPrincipal user: User) = MessageResponse(messageService.createHelloMessage("World"), user.username)

    @Operation(summary = "Post custom message", tags = ["Message API"])
    @ResponseStatus(OK)
    @PostMapping
    fun helloPostMessage(@RequestBody messageRequest: MessageRequest, @AuthenticationPrincipal user: User) =
        MessageResponse(messageService.createHelloMessage(messageRequest.message), user.username)

    data class MessageRequest(var message: String)

    data class MessageResponse(var greeting: String, val username: String)
}