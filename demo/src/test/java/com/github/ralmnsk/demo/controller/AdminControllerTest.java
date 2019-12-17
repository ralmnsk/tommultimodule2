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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin")
class AdminControllerTest {
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
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
        ;
    }

    @Test
    void news() throws Exception{

        when(newsService.countAllNews()).thenReturn(1L);
        when(paginator.pagesCount(1,1)).thenReturn(1);
        when(paginator.viewNews(anyInt(),anyInt())).thenReturn(map);
        mockMvc.perform(get("/site/inform/admin/news")
                .param("move","next")
                .param("maxResults","5")
                .sessionAttr("currentPage",1)
                .sessionAttr("pagesCount",1)
                .sessionAttr("maxResults",5))
                .andExpect(model().attribute("map",map))
                .andExpect(model().attribute("pageFlag","usersNews"))
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
        ;
    }

    @Test
    void edit() throws Exception{

        when(newsEditor.newsEdit()).thenReturn(news);

        mockMvc.perform(post("/site/inform/admin/edit")
                .param("pageFlag","newsEdit")
                .param("editNewsId",news.getIdNews().toString())
                )
                .andExpect(model().attribute("news",news))
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
        ;
    }

    @Test
    void updateNews() throws Exception{
        when(newsService.getById(anyLong())).thenReturn(news);
        when(newsUpdator.newsUpdate()).thenReturn(news);

        mockMvc.perform(post("/site/inform/admin/updatenews")
                .param("editNewsId",news.getIdNews().toString())
                .param("nameNews",news.getNameNews())
                .param("dataNews",news.getDataNews())
        )
                .andExpect(model().attribute("pageFlag","usersNews"))
                .andExpect(model().attribute("editNewsId",news.getIdNews()))
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/site/inform/admin/news?pageFlag=usersNews&editNewsId=1"))
        ;
    }

    @Test
    void deleteNews() throws Exception{
        when(userService.getById(anyLong())).thenReturn(user);
        when(newsService.getById(anyLong())).thenReturn(news);
        NewsDeleter spy= Mockito.spy(newsDeleter);
        Mockito.doNothing().when(spy).delete();


        mockMvc.perform(post("/site/inform/admin/deletenews")
                .param("editNewsId",news.getIdNews().toString())
        )
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/site/inform/admin/news"))
        ;
    }

    @Test
    void contact() throws Exception{
        List<Contact> list=new ArrayList<>();
        list.add(contact);
        Map<String,String> contactMap=new HashMap<>();
        contactMap.put("zzz","test@mail.com");
        when(contactService.countAllContacts()).thenReturn(1L);
        when(paginator.pagesCount(1,1)).thenReturn(1);
        when(contactService.findAll(anyInt(),anyInt())).thenReturn(list);
        int currentPage=1;
        mockMvc.perform(get("/site/inform/admin/contact")
                .param("move","next")
                .param("maxResults", "5")
                .sessionAttr("pageFlag","contact")
                .sessionAttr("currentPage",currentPage)
                .sessionAttr("pagesCount","1")
                .sessionAttr("maxResults","5")
        )
                .andExpect(model().attribute("mapContacts",contactMap))
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
        ;
    }

    @Test
    void comment() throws Exception{
        int currentPage=1;
        List<Contact> list=new ArrayList<>();
        list.add(contact);
        when(discussionService.count()).thenReturn(1L);
        when(paginator.pagesCount(1,1)).thenReturn(1);
        when(contactService.findAll(anyInt(),anyInt())).thenReturn(list);
        mockMvc.perform(get("/site/inform/admin/comment")
                .param("move","next")
                .param("maxResults", "5")
                .sessionAttr("pageFlag","discussions")
                .sessionAttr("currentPage",currentPage)
                .sessionAttr("pagesCount","1")
                .sessionAttr("maxResults","5")
        )
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin"))
        ;
    }

    @Test
    void msg() throws Exception{
        Map<Msg,User>msgUserMap=new HashMap<>();
        msgUserMap.put(msg,user);
        when(newsService.getById(news.getIdNews())).thenReturn(news);
        when(userService.getById(user.getId())).thenReturn(user);
        when(msgCreator.create()).thenReturn(msg);
        when(msgCreator.getMsgMap()).thenReturn(msgUserMap);
        mockMvc.perform(post("/site/inform/admin/msg")
                .param("msgText",msg.getText())
                .sessionAttr("discussNewsId",discussion.getId())
                .sessionAttr("userId",user.getId())
                .sessionAttr("news",news)
                .sessionAttr("mapMsgUsr",msgUserMap)
                )
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("discussion"))
        ;
    }

    @Test
    void discussion() throws Exception{
        Map<Msg,User>msgUserMap=new HashMap<>();
        msgUserMap.put(msg,user);
        when(newsService.getById(news.getIdNews())).thenReturn(news);
        when(userService.getById(user.getId())).thenReturn(user);
        when(msgCreator.create()).thenReturn(msg);
        when(msgCreator.getMsgMap()).thenReturn(msgUserMap);
        mockMvc.perform(get("/site/inform/admin/discuss")
                .param("msgText",msg.getText())
                .param("discussNewsId","1")
                .sessionAttr("userId",user.getId())
                .sessionAttr("news",news)
                .sessionAttr("mapMsgUsr",msgUserMap)
        )
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("msg_admin"))
        ;
    }

    @Test
    void discussionPost() throws Exception{
        Map<Msg,User>msgUserMap=new HashMap<>();
        msgUserMap.put(msg,user);
        when(newsService.getById(news.getIdNews())).thenReturn(news);
        when(userService.getById(user.getId())).thenReturn(user);
        when(msgCreator.create()).thenReturn(msg);
        when(msgCreator.getMsgMap()).thenReturn(msgUserMap);
        Map<Msg,User>msgUserMap1=new HashMap<>();
        msgUserMap.put(msg,user);
        when(newsService.getById(news.getIdNews())).thenReturn(news);
        when(userService.getById(user.getId())).thenReturn(user);
        when(msgCreator.create()).thenReturn(msg);
        when(msgCreator.getMsgMap()).thenReturn(msgUserMap);
        mockMvc.perform(post("/site/inform/admin/discuss")
                .param("msgText",msg.getText())
                .param("discussNewsId","1")
                .sessionAttr("userId",user.getId())
                .sessionAttr("news",news)
                .sessionAttr("mapMsgUsr",msgUserMap1)
        )
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().isOk())
                .andExpect(view().name("msg_admin"))
        ;
    }

    @Test
    void msgEdit() throws Exception{
        MsgService spy= Mockito.spy(msgService);
        Mockito.doNothing().when(spy).update(anyLong(),anyString());
        Mockito.doNothing().when(spy).delete(anyLong());
        mockMvc.perform(post("/site/inform/admin/msgedit")
                .param("msgText",msg.getText())
                .param("msgupdate",msg.getId().toString())
                .param("editMsgId","1")
                .param("discussNewsId","1")
        )
                .andExpect(authenticated().withRoles("ADMIN"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/site/inform/admin/discuss?discussNewsId=1"))
        ;
    }
}