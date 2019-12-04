package com.github.ralmnsk.service.news.updator;

import com.github.ralmnsk.dto.NewsDto;
import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.news.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class NewsUpdatorImpl implements NewsUpdator {
    @Autowired
    private NewsService newsService;
    @Autowired
    private ModelMapper mapper;

    private NewsDto newsDto;

    public NewsDto getNewsDto() {
        return newsDto;
    }

    public void setNewsDto(NewsDto newsDto) {
        this.newsDto = newsDto;
    }

    public NewsUpdatorImpl(NewsDto newsDto) {
        this.newsDto = newsDto;
    }

    public NewsUpdatorImpl() {
    }

    @Override
    public NewsDto newsUpdate() {
        newsService.updateNews(newsDto);
        log.info(this.getClass()+ ": news id= {}, name:{} was updated", newsDto.getIdNews(),newsDto.getNameNews());
        return newsDto;
    }
}
