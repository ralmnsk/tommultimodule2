package com.github.ralmnsk.service.news.editor;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.news.NewsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NewsEditorImpl implements NewsEditor {
    private static Logger logger= LoggerFactory.getLogger(NewsEditorImpl.class);
    private Long id;
    //private HttpSession session;

    public NewsEditorImpl(Long id) {
        this.id = id;
        //this.session=session;
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
//        NewsService newsService=NewsServiceImpl.getInstance();
//        news=newsService.getById(id);
//        logger.info(this.getClass()+" news {} was edited",news);
        return news;
    }
}
