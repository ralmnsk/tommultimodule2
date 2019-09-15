package my.tomcat.app.controllers;

import my.tomcat.app.DBEmul.DBemulation;
import my.tomcat.app.clienttype.ClientType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    private void processReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        ClientType clientType = (ClientType) session.getAttribute("userType");
        String userForGoodBuy=(String)session.getAttribute("login");

        session.setAttribute("login", null);
        session.setAttribute("password", null);
        session.setAttribute("userType", null);
        session.setAttribute("userForGoodBuy",userForGoodBuy);
        req.getRequestDispatcher("/logout.jsp").forward(req, resp);


    }
}
