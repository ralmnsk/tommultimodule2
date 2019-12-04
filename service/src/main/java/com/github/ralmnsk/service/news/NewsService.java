package com.github.ralmnsk.service.news;

import com.github.ralmnsk.dao.news.NewsDao;
import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.model.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface NewsService {

    void createNews(NewsDto newsDto);
    NewsDto readNews(NewsDto newsDto);
    void updateNews(NewsDto newsDto);
    void deleteNews(NewsDto newsDto);
    List<Long> findAllNewsByUserId(Pageable pageable, Long userId);
    List<NewsDto> findAllNews(Pageable pageable);
//    List<News> findAllNews(int page, int maxResults);
    NewsDto getById(Long id);
    NewsDto findByName(String name);
    Long countAllNews();
}
