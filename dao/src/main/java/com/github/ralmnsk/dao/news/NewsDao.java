package com.github.ralmnsk.dao.news;


import com.github.ralmnsk.model.news.News;

import java.util.List;

public interface NewsDao {
    void createNews(News news);
    News readNews(News news);
    void updateNews(News news);
    void deleteNews(News news);
    List<News> findAllNews();
}
