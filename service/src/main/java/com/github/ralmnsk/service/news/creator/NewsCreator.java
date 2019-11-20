package com.github.ralmnsk.service.news.creator;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.news.NewsService;

public interface NewsCreator {
    News newsCreate();
    void setUserId(Long userId);
    void setDataNews(String dataNews);
    void setNameNews(String nameNews);

}
