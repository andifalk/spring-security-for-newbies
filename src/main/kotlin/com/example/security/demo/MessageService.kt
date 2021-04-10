package com.example.security.demo

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service

@Service
@PreAuthorize("hasAnyRole('USER','ADMIN')")
internal class MessageService {

    fun createHelloMessage(message: String) = "Hello $message"

    @PreAuthorize("hasRole('ADMIN')")
    fun getAdminMessage() = "Hello Administrator"
}