package com.example.security.demo

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/message")
internal class MessageRestController(private val messageService: MessageService) {

    @GetMapping
    fun helloMessage() = MessageResponse("Hello World")

    @PostMapping
    fun helloPostMessage(@RequestBody messageRequest: MessageRequest): MessageResponse? {
        val messageResponse = MessageResponse(messageService.createHelloMessage(messageRequest.message))
        return messageResponse
    }

    data class MessageRequest(var message: String)

    data class MessageResponse(var greeting: String)
}