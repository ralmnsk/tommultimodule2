package com.github.ralmnsk.app.controllers.comment;


import com.github.ralmnsk.app.controllers.discussion.DiscussionServlet;
import com.github.ralmnsk.service.contact.creator.ContactCreator;
import com.github.ralmnsk.service.contact.creator.ContactCreatorImpl;
import com.github.ralmnsk.service.dispute.Dispute;
import com.github.ralmnsk.service.dispute.DisputeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ={"/site/comment"})
public class CommentServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(DiscussionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        getComment(req,resp);
        req.getRequestDispatcher("/inform.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getComment(req,resp);
        req.getRequestDispatcher("/inform.jsp").forward(req, resp);
    }

    private void getComment(HttpServletRequest req, HttpServletResponse resp){
        Dispute dispute=new DisputeImpl(req);
        dispute.get();
    }

}
