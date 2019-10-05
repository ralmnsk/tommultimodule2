package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.news.NewsService;
import com.github.ralmnsk.service.news.NewsServiceImpl;
import com.github.ralmnsk.service.storage.StorageService;
import com.github.ralmnsk.service.storage.StorageServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/site/deletenews")
public class DelNewsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
        req.getRequestDispatcher("/site/mynews").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
        req.getRequestDispatcher("/site/mynews").forward(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        News news=(News)req.getSession().getAttribute("news");
        NewsService newsService= NewsServiceImpl.getInstance();
        newsService.deleteNews(news);
        StorageService storageService= StorageServiceImpl.getInstance();
        Long userId=storageService.getUserIdByNewsId(news.getIdNews());
        storageService.deleteStorage(userId,news.getIdNews());
    }


}
