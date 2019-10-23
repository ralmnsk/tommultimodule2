package com.github.ralmnsk.service.news.updator;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

public class NewsUpdatorImpl implements NewsUpdator {
    private static Logger logger= LoggerFactory.getLogger(NewsUpdatorImpl.class);
    private HttpSession session;
    private News news;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public NewsUpdatorImpl(HttpSession session, News news) {
        this.news = news;
        this.session=session;
    }

    public NewsUpdatorImpl() {
    }

    @Override
    public void newsUpdate() {
        NewsService newsService=NewsServiceImpl.getInstance();
        newsService.updateNews(news);
        session.setAttribute("news",news);
        logger.info(this.getClass()+ " newsUpdate");
    }
}
