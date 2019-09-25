package com.github.ralmnsk.model.news;

import org.junit.Before;
import org.junit.Test;
import java.sql.Date;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class NewsTest {
    private News testNews;
    private Date date;

    @Before
    public void setUp() throws Exception {
        date=new Date(new java.util.Date().getTime());
        testNews=new News(1l,2l,"nameNews", "dataNews",date);
    }

    @Test
    public void getIdUser() {
        long idUser=testNews.getIdUser();
        assertEquals(2l,idUser);
    }

    @Test
    public void setIdUser() {
        testNews.setIdUser(333L);
        long id=testNews.getIdUser();
        assertEquals(333L,id);
    }

    @Test
    public void getIdNews() {
        long idNews=testNews.getIdNews();
        assertEquals(1l,idNews);
    }

    @Test
    public void setIdNews() {
        testNews.setIdNews(333L);
        long id=testNews.getIdNews();
        assertEquals(333L,id);
    }

    @Test
    public void getNameNews() {
        String nameNews=testNews.getNameNews();
        assertEquals("nameNews", nameNews);
    }

    @Test
    public void setNameNews() {
        testNews.setNameNews("nameNews333");
        String name=testNews.getNameNews();
        assertEquals("nameNews333",name);
    }

    @Test
    public void getDataNews() {
        String dataNews=testNews.getDataNews();
        assertEquals("dataNews", dataNews);
    }

    @Test
    public void setDataNews() {
        testNews.setDataNews("dataNews333");
        String data=testNews.getDataNews();
        assertEquals("dataNews333",data);
    }

    @Test
    public void getDateNews() {
        Date testDate=testNews.getDateNews();
        assertEquals(date,testDate);
    }

    @Test
    public void setDateNews() {
        String str="2015-03-31";
        Date date=Date.valueOf(str);
        testNews.setDateNews(date);
        Date testDate=testNews.getDateNews();
        System.out.println("date:"+date+" =?"+testDate);
        assertThat("2015-03-31",is(testDate.toString()));
    }

    @Test
    public void toString1() {
        fail();
    }
}