package my.tomcat.app.controllers;

import model.user.User;

import my.tomcat.app.clienttype.ClientType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/site/logout")
public class LogoutServlet extends HttpServlet {
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

        HttpSession session = req.getSession();
        ClientType clientType = (ClientType) session.getAttribute("userType");
        User user=(User)session.getAttribute("user");

        session.setAttribute("bye",user.getName());
        session.setAttribute("user", null);
        session.setAttribute("userType", null);
        req.getRequestDispatcher("/logout.jsp").forward(req, resp);


    }
}
