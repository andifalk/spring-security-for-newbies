package com.example.security.demo

import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AdminRestController::class)
internal class AdminRestControllerTest(
    @Autowired private val mockMvc: MockMvc
) {

    @MockBean
    private lateinit var messageService: MessageService

    @Test
    fun `get message`() {
        given(this.messageService.getAdminMessage()).willReturn("Hello Administrator")
        mockMvc.perform(get("/api/admin").with(user("user").password("secret").roles("ADMIN")))
            .andDo(print())
            .andExpect(status().isOk).andExpect(
                content().string(
                    """{"greeting":"Hello Administrator","username":"user"}"""
                )
            )
    }

    @Test
    fun `get message unauthorized`() {
        mockMvc.perform(get("/api/admin"))
            .andDo(print())
            .andExpect(status().isUnauthorized)
    }

    @Test
    fun `get message forbidden`() {
        mockMvc.perform(get("/api/admin").with(user("user").password("secret").roles("USER")))
            .andDo(print())
            .andExpect(status().isForbidden)
    }
}
