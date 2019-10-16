package com.github.ralmnsk.dao.news;


import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;

import java.util.List;

public interface NewsDao {
    void createNews(News news);
    News readNews(News news);
    void updateNews(News news);
    void deleteNews(News news);
    List<News> findAllNews(int firstResult, int maxResults);
    News getById(Long id);
    Long getUserId(Long userId);
}
