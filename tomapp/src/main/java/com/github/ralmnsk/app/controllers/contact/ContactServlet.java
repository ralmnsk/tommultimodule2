package com.github.ralmnsk.app.controllers.contact;


import com.github.ralmnsk.app.controllers.discussion.DiscussionServlet;
import com.github.ralmnsk.service.contact.creator.ContactCreator;
import com.github.ralmnsk.service.contact.creator.ContactCreatorImpl;
import com.github.ralmnsk.service.msg.MsgCreator;
import com.github.ralmnsk.service.msg.MsgCreatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns ={"/site/contact"})
public class ContactServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(DiscussionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        getContact(req,resp);
        req.getRequestDispatcher("/contact.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getContact(req,resp);
        req.getRequestDispatcher("/contact.jsp").forward(req, resp);
    }

    private void getContact(HttpServletRequest req, HttpServletResponse resp){
        ContactCreator contactCreator=new ContactCreatorImpl(req);
        contactCreator.getContact();
    }

}
