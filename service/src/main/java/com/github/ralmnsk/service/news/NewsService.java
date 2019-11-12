package com.github.ralmnsk.service.news;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.model.news.News;

import java.util.List;

public interface NewsService {

    void createNews(News news);
    News readNews(News news);
    void updateNews(News news);
    void deleteNews(News news);
    List<News> findAllNews(int page, int maxResults);
    News getById(Long id);
    News findByName(String name);
    Long countAllNews();
}
