package com.github.ralmnsk.demo.controller;


import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SiteControllerUnauthorizedTest {

    @Autowired
    private SiteController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user=new User(1L,"zzz","123",new Date(),"ROLE_USER");
    }

    @Test
    void informUnauthorized() throws Exception{
        
        mockMvc.perform(get("/site/inform"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void addNews() throws Exception {
        mockMvc.perform(get("/site/addnews"))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(view().name("addnews"))
        ;
    }

    @Test
    void createNews() {
    }

    @Test
    void news() {
    }

    @Test
    void goContact() {
    }

    @Test
    void contactPost() {
    }

    @Test
    void delContact() {
    }

    @Test
    void edit() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void comment() {
    }

    @Test
    void discussion() {
    }

    @Test
    void discussionPost() {
    }

    @Test
    void msg() {
    }
}