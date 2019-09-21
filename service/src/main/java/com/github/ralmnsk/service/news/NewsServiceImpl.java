package com.github.ralmnsk.service.news;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.model.news.News;

import java.util.List;

public class NewsServiceImpl implements NewsService{

    private NewsDao newsDao;

    public void createNews(News news) {
        newsDao.createNews(news);
    }

    public News readNews(News news) {
        return newsDao.readNews(news);
    }

    public void updateNews(News news) {
        newsDao.updateNews(news);
    }

    public void deleteNews(News news) {
        newsDao.deleteNews(news);
    }

    public void setNewsDao(NewsDao newsDao) {
        this.newsDao=newsDao;
    }

    public List<News> findAllNews() {
        return newsDao.findAllNews();
    }
}