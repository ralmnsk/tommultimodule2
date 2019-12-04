package com.github.ralmnsk.service.news.creator;

import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.news.NewsService;

public interface NewsCreator {
    NewsDto newsCreate();
    void setUserId(Long userId);
    void setDataNews(String dataNews);
    void setNameNews(String nameNews);

}
