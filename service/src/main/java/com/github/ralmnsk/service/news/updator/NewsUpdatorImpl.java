package com.github.ralmnsk.service.news.updator;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoImpl;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;

import javax.servlet.http.HttpSession;

public class NewsUpdatorImpl implements NewsUpdator {
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
        NewsDao newsDao=new NewsDaoImpl();
        NewsService newsService=new NewsServiceImpl();
        newsService.setNewsDao(newsDao);
        newsService.updateNews(news);
        session.setAttribute("news",news);
    }
}
