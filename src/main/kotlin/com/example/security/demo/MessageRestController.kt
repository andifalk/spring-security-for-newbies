package com.example.security.demo

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/message")
internal class MessageRestController(private val messageService: MessageService) {

    @GetMapping
    fun helloMessage() = "Hello World"

    @PostMapping
    fun helloPostMessage(@RequestBody messageRequest: MessageRequest) = messageService.createHelloMessage(messageRequest.message)

    data class MessageRequest(val message: String)
}