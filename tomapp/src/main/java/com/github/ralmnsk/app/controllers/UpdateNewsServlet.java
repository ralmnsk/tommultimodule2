package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.model.news.News;
import com.github.ralmnsk.service.news.editor.NewsEditor;
import com.github.ralmnsk.service.news.editor.NewsEditorImpl;
import com.github.ralmnsk.service.news.updator.NewsUpdator;
import com.github.ralmnsk.service.news.updator.NewsUpdatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/site/updatenews")
public class UpdateNewsServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(IndexServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        processReq(req,resp);
    }

    private void processReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //User user=(User)req.getSession().getAttribute("user");
        HttpSession session = req.getSession();
        News news=(News)req.getSession().getAttribute("news");
        String nameNews=req.getParameter("nameNews");
        String dataNews=req.getParameter("dataNews");
        news.setNameNews(nameNews);
        news.setDataNews(dataNews);
        NewsUpdator newsUpdator=new NewsUpdatorImpl(session,news);
        newsUpdator.newsUpdate();

        req.getRequestDispatcher("/inform.jsp").forward(req, resp);
        logger.info(this.getClass()+" processReq /inform.jsp");
    }
}
