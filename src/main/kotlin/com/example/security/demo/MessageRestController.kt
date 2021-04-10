package com.example.security.demo

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/message")
internal class MessageRestController(private val messageService: MessageService) {

    @GetMapping
    fun helloMessage() = MessageResponse(messageService.createHelloMessage("World"))

    @PostMapping
    fun helloPostMessage(@RequestBody messageRequest: MessageRequest): MessageResponse? {
        return MessageResponse(messageService.createHelloMessage(messageRequest.message))
    }

    data class MessageRequest(var message: String)

    data class MessageResponse(var greeting: String)
}