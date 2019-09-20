package dao.news;

import model.news.News;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Properties;

import static org.junit.Assert.*;

public class NewsDaoImplTest {
    private News news;

    @Before
    public void setUp(){
        Properties properties=new Properties();
        try {
            properties
                    .load(getClass()
                            .getClassLoader()
                            .getResourceAsStream("daotest.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String nameNews=properties.getProperty("namenews");
        String dataNews=properties.getProperty("datanews");
        try {
            nameNews= new String(nameNews.getBytes("UTF-8"), "windows-1251");
            dataNews= new String(dataNews.getBytes("windows-1251"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        news=new News(1l,nameNews,
                dataNews,
                new Date(new java.util.Date().getTime()));
    }

    @Test
    public void createNews() {
        NewsDaoImpl newsDao=new NewsDaoImpl();
        newsDao.createNews(news);
    }

    @Test
    public void readNews() {
        NewsDaoImpl newsDao=new NewsDaoImpl();
        News readNews = newsDao.readNews(this.news);
        System.out.println(readNews.toString());

    }

    @Test
    public void updateNews() {
    }

    @Test
    public void deleteNews() {
    }

    @Test
    public void findAllNews() {
    }
}