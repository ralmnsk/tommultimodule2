package com.github.ralmnsk.model.news;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.sql.Timestamp;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class NewsTest {
    private News testNews;
    private Timestamp date;

    @BeforeAll
    public void setUp() throws Exception {
        date=new Timestamp(new java.util.Date().getTime());
        testNews=new News(1L,2L,"nameNews", "dataNews",date);
    }

    @Test
    public void getIdUser() {
        long idUser=testNews.getIdUser();
        assertEquals(2L,idUser);
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
        Timestamp testDate=testNews.getDateNews();
        assertEquals(date,testDate);
    }

    @Test
    public void setDateNews() {
        String str="2015-03-31 01:01:10.0";
        Timestamp date=Timestamp.valueOf(str);
        testNews.setDateNews(date);
        Timestamp testDate=testNews.getDateNews();
        System.out.println("date:"+date+" =?"+testDate);
        assertSame(str, (testDate.toString()));
    }

    @Test
    public void toString1() {
        String str="News{idNews=1, idUser=2, nameNews='nameNews', dataNews='dataNews', dateNews="+date+"}";
        assertSame(str, (testNews.toString()));
    }
}