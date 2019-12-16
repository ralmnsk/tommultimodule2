package com.github.ralmnsk.demo.controller;


import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.contact.creator.ContactCreator;
import com.github.ralmnsk.service.deleter.NewsDeleter;
import com.github.ralmnsk.service.deleter.NewsDeleterImpl;
import com.github.ralmnsk.service.discussion.DiscussionService;
import com.github.ralmnsk.service.dispute.Dispute;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.creator.NewsCreator;
import com.github.ralmnsk.service.news.editor.NewsEditor;
import com.github.ralmnsk.service.news.updator.NewsUpdator;
import com.github.ralmnsk.service.pagination.Paginator;
import com.github.ralmnsk.service.user.UserService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;
import java.util.*;

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
    @MockBean
    private NewsEditor newsEditor;
    @MockBean
    private NewsService newsService;
    @MockBean
    private NewsUpdator newsUpdator;
    @MockBean
    private NewsDeleter newsDeleter;
    @MockBean
    private DiscussionService discussionService;
    @MockBean
    private Dispute dispute;

    private User user;
    private News news;
    private Map<News,User> map;
    private Contact contact;
    private Discussion discussion;


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
        user.setContact(contact);

        discussion=new Discussion();
        discussion.setId(1L);
        discussion.getUserSet().add(user);
        user.getDiscussionSet().add(discussion);
        discussion.setNews(news);
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
        when(contactCreator.getContact(anyObject(),anyString())).thenReturn(contact);
        mockMvc.perform(post("/site/gocontact")
                .param("mail","test@mail.com")
                .param("userId","1")
        )
                .andExpect(model().attribute("mail","test@mail.com"))
                .andExpect(model().attribute("contact",contact))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(view().name("contact"))
        ;
    }

    @Test
    void contactPost() throws Exception{
        when(userService.getById(any())).thenReturn(user);
//        when(contactCreator.getContact(anyObject(),anyString())).thenReturn(contact);
        mockMvc.perform(get("/site/contact")
                .param("mail","test@mail.com")
                .param("userId","1")
        )
//                .andExpect(model().attribute("mail","test@mail.com"))
                .andExpect(model().attribute("contact",contact))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(view().name("contact"))
        ;
    }

    @Test
    void delContact() throws Exception{
        Contact delContact=new Contact();
        delContact.setId(2L);
        delContact.setMail("no");
        delContact.setUser(user);
        when(userService.getById(any())).thenReturn(user);
        when(userService.readUser(user)).thenReturn(user);
        when(contactCreator.delContact(user)).thenReturn(delContact);
        mockMvc.perform(post("/site/delcontact")
                .param("editNewsId","1")
        )
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(view().name("contact"))
        ;
    }

    @Test
    void edit() throws Exception {
        when(newsEditor.newsEdit()).thenReturn(news);
        mockMvc.perform(post("/site/edit")
                .param("userId","1")
                .param("editNewsId","1")
        )
                .andExpect(model().attribute("news",news))
                .andExpect(model().attribute("editNewsId",news.getIdNews()))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(view().name("editnews"))
        ;
    }

    @Test
    void update() throws Exception{
        when(newsService.getById(1L)).thenReturn(news);
        when(newsUpdator.newsUpdate()).thenReturn(news);
        mockMvc.perform(post("/site/updatenews")
                .param("userId",news.getIdNews().toString())
                .param("editNewsId",news.getIdNews().toString())
                .param("nameNews",news.getNameNews())
                .param("dataNews",news.getDataNews())
        )
                .andExpect(model().attribute("news",news))
                .andExpect(model().attribute("editNewsId",news.getIdNews()))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(view().name("inform"))
        ;
    }

    @Test
    void delete() throws Exception{
        newsDeleter=new NewsDeleterImpl();
        NewsDeleter spy= Mockito.spy(newsDeleter);
        when(newsService.getById(news.getIdNews())).thenReturn(news);
        when(userService.getById(anyLong())).thenReturn(user);
        Mockito.doNothing().when(spy).delete();
        mockMvc.perform(post("/site/deletenews")
                .param("userId",news.getIdNews().toString())
                .param("editNewsId",news.getIdNews().toString())
        )
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/site/mynews"))
        ;
    }

    @Test
    void comment() throws Exception{
        List<Discussion> list=new ArrayList<>();
        list.add(discussion);
        when(userService.getById(user.getId())).thenReturn(user);
        when(dispute.get(user)).thenReturn(list);
        when(paginator.pagesCount(1,1)).thenReturn(1);
        when(discussionService.readByUser(user)).thenReturn(list);
        mockMvc.perform(get("/site/comment")
                .param("userId",user.getId().toString())
                .param("move","next")
                .param("maxResult","5")
                .sessionAttr("pageFlag","myComments")
                .sessionAttr("pagesCount","1")
                .param("currentPage","1")
        )
//                .andExpect(model().attribute("discussionList",list))
                .andExpect(authenticated())
                .andExpect(status().isOk())
                .andExpect(view().name("inform"))
        ;
    }


//        session.setAttribute("pageFlag","myComments"); //changes
//        session.setAttribute("currentPage",currentPage);
//        session.setAttribute("pagesCount",pagesCount);
//        session.setAttribute("maxResults",maxResultsCount);

    @Test
    void discussion() throws Exception{
    }

    @Test
    void discussionPost() throws Exception{
    }

    @Test
    void msg() throws Exception{
    }
}