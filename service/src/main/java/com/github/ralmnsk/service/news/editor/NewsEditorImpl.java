package com.github.ralmnsk.service.news.editor;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

public class NewsEditorImpl implements NewsEditor {
    private static Logger logger= LoggerFactory.getLogger(NewsEditorImpl.class);
    private Long id;
    private HttpSession session;

    public NewsEditorImpl(HttpSession session, Long id) {
        this.id = id;
        this.session=session;
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
    public void newsEdit() {
        NewsService newsService=NewsServiceImpl.getInstance();
        News news=newsService.getById(id);
        session.setAttribute("news",news);
        logger.info(this.getClass()+" newsEdit");
    }
}
