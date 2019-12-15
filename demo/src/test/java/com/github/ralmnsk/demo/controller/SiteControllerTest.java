package com.github.ralmnsk.demo.controller;


import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.contact.creator.ContactCreator;
import com.github.ralmnsk.service.news.creator.NewsCreator;
import com.github.ralmnsk.service.pagination.Paginator;
import com.github.ralmnsk.service.user.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("zzz")
class SiteControllerTest {

    @Autowired
    private SiteController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private Paginator paginator;
    @MockBean
    private NewsCreator creator;
    @MockBean
    private HttpSession session;
    @MockBean
    private ContactCreator contactCreator;

    private User user;
    private News news;
    private Map<News,User> map;
    private Contact contact;


    @BeforeEach
    void setUp() {
        user=new User(1L,"zzz","123",new Date(),"ROLE_USER");
        news=new News(1L,"test name","test data",new Date());
        user.getNewsSet().add(news);
        map=new HashMap<>();
        map.put(news,user);
        contact=new Contact();
        contact.setId(1L);
        contact.setMail("test@mail.com");
        contact.setUser(user);
    }


    @Test
    void inform() throws Exception{
        when(userService.getById(any())).thenReturn(user);

        mockMvc.perform(get("/site/inform"))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("inform"))
        ;
    }

    @Test
    void informUnauthorized() throws Exception{

        mockMvc.perform(get("/site/inform"))
                .andExpect(unauthenticated())
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
    void createNews() throws Exception{
        mockMvc.perform(post("/site/createnews")
                .param("dataNews","dataNews")
                .param("nameNews","nameNews")
                    )
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(view().name("inform"))
        ;

    }

    @Test
    void news() throws Exception{
        when(userService.getById(any())).thenReturn(user);
        when(paginator.pagesCount(1,1)).thenReturn(1);
        int currentPage=1;
        mockMvc.perform(get("/site/mynews")
                .param("move","next")
                .param("maxResults","5")
                .sessionAttr("pageFlag","myNews")
                .sessionAttr("currentPage",currentPage)
                .sessionAttr("pagesCount","1")
                )
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(view().name("mynews"))
        ;
    }

    @Test
    void goContact() throws Exception{
        when(userService.getById(any())).thenReturn(user);
        when(contactCreator.getContact(user,anyString())).thenReturn(contact);
        mockMvc.perform(get("/site/gocontact")
                .param("mail","test@mail.com")

        )
                .andExpect(model().attribute("mail",anyString()))
                .andExpect(model().attribute("contact",anyString()))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(view().name("contact"))
        ;
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