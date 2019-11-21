package com.github.ralmnsk.service.news.updator;

import com.github.ralmnsk.model.news.News;

public interface NewsUpdator {
    News newsUpdate();
    void setNews(News news);
}
