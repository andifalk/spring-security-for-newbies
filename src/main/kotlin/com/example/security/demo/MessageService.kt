package com.example.security.demo

import org.springframework.stereotype.Service

@Service
class MessageService {

    fun createHelloMessage(message: String) = "Hello $message"
}