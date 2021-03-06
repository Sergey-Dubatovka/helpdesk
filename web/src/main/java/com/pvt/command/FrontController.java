package com.pvt.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/do",name="FrontController")
public class FrontController extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action=Actions.defineFrom(req);
        Action nexAction=action.execute(req);
        if (nexAction==null) {
        RequestDispatcher reqDispatcher=getServletContext().getRequestDispatcher(action.getJsp());
        reqDispatcher.forward(req,resp);
        }
        else
           resp.sendRedirect("do?command="+nexAction);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action=Actions.defineFrom(req);
        action.execute(req);
        RequestDispatcher reqDispatcher = getServletContext().getRequestDispatcher(action.getJsp());
        reqDispatcher.forward(req,resp);
    }
}
