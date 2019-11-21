package com.github.ralmnsk.service.news.editor;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.news.NewsService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NewsEditorImpl implements NewsEditor {
    @Autowired
    private NewsService newsService;
    private Long id;

    public NewsEditorImpl(Long id) {
        this.id = id;
    }

    public NewsEditorImpl() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public News newsEdit() {
        News news=null;
        news=newsService.getById(id);
        log.info(this.getClass()+" news {} was edited",news);
        return news;
    }
}
