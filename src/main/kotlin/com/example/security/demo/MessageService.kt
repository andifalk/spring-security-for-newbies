package com.example.security.demo

import org.springframework.stereotype.Service

@Service
internal class MessageService {

    fun createHelloMessage(message: String) = "Hello $message"

    fun getAdminMessage() = "Hello Administrator"

}