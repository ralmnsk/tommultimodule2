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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
        mockMvc.perform(post("/site/addnews"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void createNews() throws Exception{
        mockMvc.perform(post("/site/createnews"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void news() throws Exception{
        mockMvc.perform(post("/site/mynews"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void goContact() throws Exception{
        mockMvc.perform(post("/site/gocontact"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void contactPost() throws Exception{
        mockMvc.perform(post("/site/contact"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void delContact() throws Exception{
        mockMvc.perform(post("/site/delcontact"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void edit() throws Exception{
        mockMvc.perform(post("/site/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void update() throws Exception{
        mockMvc.perform(post("/site/updatenews"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void delete() throws Exception{
        mockMvc.perform(post("/site/deletenews"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void comment() throws Exception{
        mockMvc.perform(post("/site/comment"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void discussion() throws Exception{
        mockMvc.perform(get("/site/discuss"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void discussionPost() throws Exception{
        mockMvc.perform(post("/site/discuss"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    void msg() throws Exception{
        mockMvc.perform(post("/site/msg"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }
}