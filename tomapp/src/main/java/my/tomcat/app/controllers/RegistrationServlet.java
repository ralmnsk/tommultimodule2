package my.tomcat.app.controllers;


import dao.connection.SingletonConnection;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import model.user.User;
import service.user.UserService;
import service.user.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
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
    }

    private void registration(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password=req.getParameter("password");
        User user=new User();
        user.setName(login);
        user.setPass(password);
        user.setJoinDate(new Date(new java.util.Date().getTime()));
        user.setRole("usr");

        UserDao userDao=new UserDaoImpl();
        UserService userService=new UserServiceImpl();
        userService.setUserDao(userDao);

        User readUser=userService.readUser(user);
        System.out.println("read user:"+readUser);
        HttpSession session=req.getSession();
        if(!user.getName().equals(readUser.getName())){
            userService.createUser(user);
            String msg="Регистация "+user.getName()+" прошла успешно.";
            session.setAttribute("message", msg);
        } else{
            String msg=user.getName()+" уже зарегистрирован.";
            session.setAttribute("message", msg);
        }

//        try {
//            SingletonConnection.getInstance().getConnection().close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }


}
