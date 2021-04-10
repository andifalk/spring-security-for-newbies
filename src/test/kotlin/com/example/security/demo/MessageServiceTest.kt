package com.example.security.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class MessageServiceTest(@Autowired private val messageService: MessageService) {

    @Test
    fun `create greeting`() {
        val greeting = messageService.createHelloMessage("Test")
        assertThat(greeting).isEqualTo("Hello Test")
    }

    @Test
    fun `get admin greeting`() {
        val greeting = messageService.getAdminMessage()
        assertThat(greeting).isEqualTo("Hello Administrator")
    }
}