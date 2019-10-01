package com.github.ralmnsk.service.news.creator;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoImpl;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class NewsCreatorImpl implements NewsCreator {
    private User user;
    private String dataNews;
    private String nameNews;

    private static Logger logger= LoggerFactory.getLogger(NewsCreatorImpl.class);

    public NewsCreatorImpl(User user, String dataNews, String nameNews) {
        this.user = user;
        this.dataNews = dataNews;
        this.nameNews=nameNews;
    }

    @Override
    public News newsCreate(){
        News news=new News();
        NewsDao newsDao=new NewsDaoImpl();
        NewsService newsService=new NewsServiceImpl();
        newsService.setNewsDao(newsDao);

        if (!dataNews.isEmpty()){
            news=new News(user.getId(), nameNews, dataNews, new Timestamp(new java.util.Date().getTime()));
            logger.info("news created:"+news.toString());
            newsService.createNews(news);

        } else {
            logger.info("news created:"+news.toString()+" is empty");
        }

    return news;
    }


}
