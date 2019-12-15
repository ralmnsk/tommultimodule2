package com.github.ralmnsk.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class LoginControllerTest {
    @Autowired
    private LoginController controller;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void login() throws Exception{
        assertNotNull(controller);
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void welcomeUnAuthorized() throws Exception{
        mockMvc.perform(get("/welcome"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    void correctLoginPost () throws Exception {
        mockMvc.perform(formLogin("/login").userParameter("login")
                .user("zzz").password("123"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
    }

    @Test
    void incorrectLoginPost () throws Exception {
        mockMvc.perform(formLogin("/login")
                .userParameter("login")
                .user("zzz1").password("123"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void logoutPage() throws Exception{
        mockMvc.perform(get("/site/logout"))
                .andExpect(unauthenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }
}