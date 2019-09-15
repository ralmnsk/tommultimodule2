package my.tomcat.app.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inform")
public class InformServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processReq(req,resp);
    }

    private void processReq(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
//        RequestDispatcher dispatcher = getServletContext()
//                .getRequestDispatcher("/jsp/login.jsp");
//        dispatcher.forward(req, resp);
        //resp.sendRedirect(req.getContextPath()+"/jsp/login.jsp");
        req.getRequestDispatcher("/inform.jsp").forward(req, resp);
    }
}
