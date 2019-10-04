package com.github.ralmnsk.app.controllers;


import com.github.ralmnsk.dao.user.UserDao;
import com.github.ralmnsk.dao.user.UserDaoImpl;
import com.github.ralmnsk.model.user.User;
import com.github.ralmnsk.service.user.UserService;
import com.github.ralmnsk.service.user.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
    private static Logger logger= LoggerFactory.getLogger(IndexServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    private void processReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        session.setAttribute("registration",null);
        registration(req,resp);
        req.getRequestDispatcher("/reg_succsses.jsp").forward(req, resp);
        logger.info(this.getClass()+" /reg_succsses.jsp");
    }

    private void registration(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password=req.getParameter("password");
        User user=new User();
        user.setName(login);
        user.setPass(password);
        user.setJoinDate(new Timestamp(new java.util.Date().getTime()));
        user.setRole("usr");

//        UserDao userDao=new UserDaoImpl();
        UserService userService=UserServiceImpl.getInstance();
//        userService.setUserDao(userDao);

        User readUser=userService.readUser(user);
        //System.out.println("read user:"+readUser);
        HttpSession session=req.getSession();
        if(!user.getName().equals(readUser.getName())){
            userService.createUser(user);
            String msg="Регистация "+user.getName()+" прошла успешно.";
            session.setAttribute("message", msg);
        } else{
            String msg=user.getName()+" уже зарегистрирован.";
            session.setAttribute("message", msg);
        }
    }


}
