package com.github.ralmnsk.service.news.updator;

import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.model.news.News;

public interface NewsUpdator {
    NewsDto newsUpdate();
    void setNewsDto(NewsDto newsDto);
}
