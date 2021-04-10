package com.example.security.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.test.context.support.WithAnonymousUser
import org.springframework.security.test.context.support.WithMockUser

@SpringBootTest
internal class MessageServiceTest(@Autowired private val messageService: MessageService) {

    @WithMockUser
    @Test
    fun `create greeting`() {
        val greeting = messageService.createHelloMessage("Test")
        assertThat(greeting).isEqualTo("Hello Test")
    }

    @WithAnonymousUser
    @Test
    fun `create greeting forbidden`() {
        assertThrows<AccessDeniedException> { messageService.createHelloMessage("Test") }
    }

    @WithMockUser(roles = ["ADMIN"])
    @Test
    fun `get admin greeting`() {
        val greeting = messageService.getAdminMessage()
        assertThat(greeting).isEqualTo("Hello Administrator")
    }

    @WithMockUser(roles = ["USER"])
    @Test
    fun `get admin greeting forbidden`() {
        assertThrows<AccessDeniedException> { messageService.getAdminMessage() }
    }

    @WithAnonymousUser
    @Test
    fun `get admin greeting anonymous forbidden`() {
        assertThrows<AccessDeniedException> { messageService.getAdminMessage() }
    }
}