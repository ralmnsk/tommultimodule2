package com.github.ralmnsk.service.news.updator;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.news.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class NewsUpdatorImpl implements NewsUpdator {
    @Autowired
    private NewsService newsService;

    private News news;

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public NewsUpdatorImpl(News news) {
        this.news = news;
    }

    public NewsUpdatorImpl() {
    }

    @Override
    public News newsUpdate() {
        newsService.updateNews(news);
        log.info(this.getClass()+ ": news id= {}, name:{} was updated", news.getIdNews(),news.getNameNews());
        return news;
    }
}
