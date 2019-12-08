package com.github.ralmnsk.demo.controller;


import static
        org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.pagination.Paginator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(IndexController.class)
public class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsService newsService;
    @MockBean
    private Paginator paginator;


        @Test
    public void news() throws Exception {
        mockMvc.perform(get("/news")
                .sessionAttr("pageFlag", "allNews")
                .sessionAttr("currentPage",1)
//                .sessionAttr("pagesCount",any())
                .sessionAttr("maxResults",5)
        )

                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("map"))

                ;
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/")
                )

                .andExpect(status().is  (302))
                .andExpect(redirectedUrl("/news"))
        ;
    }
}