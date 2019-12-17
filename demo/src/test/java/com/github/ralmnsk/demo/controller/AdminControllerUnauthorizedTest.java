package com.github.ralmnsk.demo.controller;

import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.msg.Msg;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.contact.ContactService;
import com.github.ralmnsk.service.deleter.NewsDeleter;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.msg.MsgCreator;
import com.github.ralmnsk.service.msg.MsgService;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.editor.NewsEditor;
import com.github.ralmnsk.service.news.updator.NewsUpdator;
import com.github.ralmnsk.service.pagination.Paginator;
import com.github.ralmnsk.service.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("zzz")
class AdminControllerUnauthorizedTest {
    @Autowired
    private SiteController controller;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private Paginator paginator;
    @MockBean
    private NewsService newsService;
    @MockBean
    private NewsEditor newsEditor;
    @MockBean
    private NewsUpdator newsUpdator;
    @MockBean
    private NewsDeleter newsDeleter;
    @MockBean
    private ContactService contactService;
    @MockBean
    private DiscussionService discussionService;
    @MockBean
    private MsgCreator msgCreator;
    @MockBean
    private MsgService msgService;

    private User user;
    private News news;
    private Map<News,User> map;
    private Msg msg;
    private Contact contact;
    private Discussion discussion;


    @BeforeEach
    void setUp() {
        user=new User(1L,"zzz","123",new Date(),"ROLE_USER");
        news=new News(1L,"test name","test data",new Date());
        news.setUser(user);
        user.getNewsSet().add(news);
        map=new HashMap<>();
        msg=new Msg(new Date(),"test text");
        msg.setId(1L);
        map.put(news,user);

        contact=new Contact();
        contact.setId(1L);
        contact.setMail("test@mail.com");
        contact.setUser(user);
        user.setContact(contact);

        discussion=new Discussion();
        discussion.setId(1L);
        discussion.getUserSet().add(user);
        user.getDiscussionSet().add(discussion);
        discussion.setNews(news);
    }

    @Test
    void adminPage() throws Exception{
        mockMvc.perform(get("/site/inform/admin"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(status().is4xxClientError())
                ;
    }

    @Test
    void news() throws Exception{
        mockMvc.perform(get("/site/inform/admin/news"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(status().is4xxClientError())
        ;

    }

    @Test
    void edit() throws Exception{
        mockMvc.perform(post("/site/inform/admin/edit"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(status().is4xxClientError())
        ;
    }

    @Test
    void updateNews() throws Exception{
        mockMvc.perform(post("/site/inform/admin/updatenews"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(status().is4xxClientError())
        ;

    }

    @Test
    void deleteNews() throws Exception{
        mockMvc.perform(post("/site/inform/admin/deletenews"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(status().is4xxClientError())
        ;

    }

    @Test
    void contact() throws Exception{
        mockMvc.perform(get("/site/inform/admin/contact"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(status().is4xxClientError())
        ;

    }


    @Test
    void comment() throws Exception{
        mockMvc.perform(get("/site/inform/admin/comment"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(status().is4xxClientError())
        ;

    }

    @Test
    void msg() throws Exception{
        mockMvc.perform(post("/site/inform/admin/msg"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(status().is4xxClientError())
        ;

    }

    @Test
    void discussion() throws Exception{
        mockMvc.perform(get("/site/inform/admin/discuss"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(status().is4xxClientError())
        ;

    }

    @Test
    void discussionPost() throws Exception{
        mockMvc.perform(post("/site/inform/admin/discuss"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(status().is4xxClientError())
        ;

    }

    @Test
    void msgEdit() throws Exception{
        mockMvc.perform(post("/site/inform/admin/msgedit"))
                .andExpect(authenticated().withRoles("USER"))
                .andExpect(status().is4xxClientError())
        ;

    }
}