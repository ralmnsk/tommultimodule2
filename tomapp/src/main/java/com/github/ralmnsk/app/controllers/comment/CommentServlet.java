package com.github.ralmnsk.app.controllers.comment;


import com.github.ralmnsk.app.controllers.discussion.DiscussionServlet;
import com.github.ralmnsk.model.discussion.Discussion;
import com.github.ralmnsk.model.user.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

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
        req.getSession().removeAttribute("discussionList");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getComment(req,resp);
        req.getRequestDispatcher("/inform.jsp").forward(req, resp);
        req.getSession().removeAttribute("discussionList");
    }

    private void getComment(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        Dispute dispute=new DisputeImpl();
        List<Discussion> discussionList=dispute.get(user);
        if ((discussionList!=null)&&(discussionList.size()>0)){
            session.setAttribute("discussionList",discussionList);
        }
    }

}
