package com.example.security.demo

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.anyString
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(MessageRestController::class)
internal class MessageRestControllerTest(
    @Autowired private val mockMvc: MockMvc,
    @Autowired private val objectMapper: ObjectMapper
) {

    @MockBean
    private lateinit var messageService: MessageService

    @Test
    fun `get message`() {
        given(this.messageService.createHelloMessage(anyString())).willReturn("Hello World")
        mockMvc.perform(
            get("/api/message").with(user("user").password("secret").roles("ADMIN"))
        )
            .andDo(print())
            .andExpect(status().isOk).andExpect(
                content().string(
                    """{"greeting":"Hello World"}"""
                )
            )
    }

    @Test
    fun `post message`() {
        given(this.messageService.createHelloMessage(anyString())).willReturn("Hello test")

        mockMvc.perform(
            post("/api/message").with(csrf()).with(user("user")).contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(MessageRestController.MessageRequest(message = "test")))
        )
            .andDo(print())
            .andExpect(status().isOk).andExpect(content().string("""{"greeting":"Hello test"}"""))
    }
}