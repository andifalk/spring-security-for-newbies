package com.example.security.demo

import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt
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
        mockMvc.perform(get("/api/admin").with(jwt().jwt { it.claim("family_name", "user") }
            .authorities(SimpleGrantedAuthority("ROLE_ADMIN"))))
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
        mockMvc.perform(get("/api/admin").with(jwt().jwt { it.claim("family_name", "user") }
            .authorities(SimpleGrantedAuthority("ROLE_USER"))))
            .andDo(print())
            .andExpect(status().isForbidden)
    }
}
