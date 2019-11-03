package com.github.ralmnsk.app.controllers.contact;


import com.github.ralmnsk.app.controllers.discussion.DiscussionServlet;
import com.github.ralmnsk.model.contact.Contact;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.contact.creator.ContactCreator;
import com.github.ralmnsk.service.contact.creator.ContactCreatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns ={"/site/delcontact"})
public class ContactDeleteServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(DiscussionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        delContact(req,resp);
        req.getRequestDispatcher("/contact.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        delContact(req,resp);
        req.getRequestDispatcher("/contact.jsp").forward(req, resp);
    }

    private void delContact(HttpServletRequest req, HttpServletResponse resp){
        ContactCreator contactCreator=new ContactCreatorImpl();
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        Contact contact=contactCreator.delContact(user);
        session.setAttribute("contact",contact);
    }

}
