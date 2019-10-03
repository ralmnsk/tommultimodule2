package com.github.ralmnsk.app.controllers;

import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.news.creator.NewsCreator;
import com.github.ralmnsk.service.news.creator.NewsCreatorImpl;
import com.github.ralmnsk.service.news.editor.NewsEditor;
import com.github.ralmnsk.service.news.editor.NewsEditorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/site/edit")
public class EditNewsServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(EditNewsServlet.class);

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
        Long editNewsId=Long.parseLong(req.getParameter("editNewsId"));
        HttpSession session = req.getSession();
        NewsEditor newsEditor=new NewsEditorImpl(session,editNewsId);
        newsEditor.newsEdit();

        req.getRequestDispatcher("/editnews.jsp").forward(req, resp);
        logger.info(this.getClass()+" processReq /editnews.jsp");
    }
}
