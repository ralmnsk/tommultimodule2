package com.github.ralmnsk.service.news;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dao.news.NewsDaoHiberImpl;
import com.github.ralmnsk.model.news.News;
import java.util.List;

public class NewsServiceImpl implements NewsService{
    private NewsDao newsDao= NewsDaoHiberImpl.getInstance();

    private static volatile NewsService instance;

    public static NewsService getInstance() {
        NewsService localInstance = instance;
        if (localInstance == null) {
            synchronized (NewsService.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new NewsServiceImpl();
                }
            }
        }
        return localInstance;
    }
    @Override
    public void createNews(News news) {
        newsDao.createNews(news);
    }
    @Override
    public News readNews(News news) {
        return newsDao.readNews(news);
    }

    @Override
    public News getById(Long id) {
        return newsDao.getById(id);
    }
    @Override
    public void updateNews(News news) {
        newsDao.updateNews(news);
    }
    @Override
    public void deleteNews(News news) {
        newsDao.deleteNews(news);
    }
    @Override
    public void setNewsDao(NewsDao newsDao) {
        this.newsDao=newsDao;
    }

    public List<News> findAllNews(int firstResult, int maxResults) {
        return newsDao.findAllNews(firstResult,maxResults);
    }
}
