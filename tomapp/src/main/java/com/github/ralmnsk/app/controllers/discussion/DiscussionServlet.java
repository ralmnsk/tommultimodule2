package com.github.ralmnsk.app.controllers.discussion;

import com.github.ralmnsk.service.msg.MsgCreator;
import com.github.ralmnsk.service.msg.MsgCreatorImpl;
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

@WebServlet(urlPatterns ={"/site/discuss"})
public class DiscussionServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(DiscussionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        discuss(req,resp);
        req.getRequestDispatcher("/discussion.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        discuss(req,resp);
        req.getRequestDispatcher("/discussion.jsp").forward(req, resp);
    }

    private void discuss(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session=req.getSession();
        Long discussNewsId=Long.parseLong(req.getParameter("discussNewsId"));
        NewsEditor newsEditor=new NewsEditorImpl(discussNewsId);
        session.setAttribute("news",newsEditor.newsEdit());
        MsgCreator msgCreator=new MsgCreatorImpl(req,discussNewsId);
        msgCreator.getMsgList();

        session.setAttribute("discussNewsId",discussNewsId);
    }

}
