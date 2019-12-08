package com.github.ralmnsk.demo.controller;

import com.github.ralmnsk.dao.connection.JpaConfig;
import com.github.ralmnsk.demo.DemoApplication;
import com.github.ralmnsk.demo.config.WebConfiguration;
import com.github.ralmnsk.demo.security.SecurityConfig;
import com.github.ralmnsk.demo.security.SecurityWebApplicationInitializer;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceRepoImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ViewResolver;

import java.util.Date;
import java.util.UUID;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SiteController.class)
@ContextConfiguration(loader= AnnotationConfigWebContextLoader.class,
        classes={JpaConfig.class, WebConfiguration.class, ViewResolver.class,
                    SecurityConfig.class, SiteController.class,
                    UserServiceRepoImpl.class})
class SiteControllerTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    MockHttpSession mockHttpSession;
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private UserServiceRepoImpl userService;
    @InjectMocks
    private SiteController siteController;

    private User user;

    @BeforeEach
    void setUp() {
        user=new User(1L,"admin","123",new Date(),"ROLE_ADMIN");
        when(userService.getById(1L)).thenReturn(user);
//        MockHttpSession mockHttpSession=new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());
        mockHttpSession.setAttribute("userId",1L);
    }

    @Test
    void inform() throws Exception{

        mockMvc.perform(get("/site/inform")
                .session(mockHttpSession)
                )

                .andExpect(status().isOk())
                .andExpect(view().name("inform"))
                .andExpect(model().attributeExists("user"))

        ;
    }

    @Test
    void addNews() {
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