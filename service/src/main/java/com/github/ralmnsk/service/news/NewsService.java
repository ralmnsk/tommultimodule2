package com.github.ralmnsk.service.news;

import com.github.ralmnsk.model.news.News;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface NewsService {

    void createNews(News news);
    News readNews(News news);
    void updateNews(News news);
    void deleteNews(News news);
    List<Long> findAllNewsByUserId(Pageable pageable, Long userId);
    List<News> findAllNews(Pageable pageable);
    News getById(Long id);
    News findByName(String name);
    Long countAllNews();
}
