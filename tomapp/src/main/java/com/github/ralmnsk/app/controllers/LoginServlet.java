package com.github.ralmnsk.app.controllers;


import com.github.ralmnsk.service.authorization.Authorization;
import com.github.ralmnsk.service.authorization.AuthorizationImpl;
import com.github.ralmnsk.service.clienttype.ClientType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
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
        String login =req.getParameter("login");
        String password=req.getParameter("password");
        //System.out.println(login+" "+password);

        HttpSession session = req.getSession();
        ClientType clientType=(ClientType)session.getAttribute("userType");
        String page=null;
        Authorization authorization=new AuthorizationImpl();

        if(authorization.process(login,password)){
            session.setAttribute("user",authorization.getUserInLoginServlet());
            session.setAttribute("userType", authorization.getClientType());
            //System.out.println("user "+login+" has role: " +authorization.getUserInLoginServlet().getRole());
            page="/welcome.jsp";
        }else {
            page="/login.jsp";
            req.setAttribute("errorLoginPassMessage","Incorrect login or password");
        }
        req.getRequestDispatcher(page).forward(req, resp);
        logger.info(this.getClass()+ " processReq "+page);
    }

}
