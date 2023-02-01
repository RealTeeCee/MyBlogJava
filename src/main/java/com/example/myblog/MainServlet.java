package com.example.myblog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//@WebServlet(urlPatterns = {"/home","*.do"}, initParams = {@WebInitParam(name = "productName", value = "My blog")}) //Cấu hình Route
@WebServlet(urlPatterns = {"/home","*.do"}, name = "Main") //Cấu hình Route
public class MainServlet extends HttpServlet {
    String productName = "";
    String connectionStr = "";
    @Override
    public void init() throws ServletException {
        productName = getInitParameter("productName");
        connectionStr = getServletContext().getInitParameter("connectionStr");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/xml");
        String name = req.getParameter("name");
        resp.getWriter().write(String.format("<application><msg>Hello %s world</msg><product>%s</product><connectionStr>%s</connectionStr></application>",name,productName,connectionStr));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if(name != null && name != ""){
            resp.getWriter().write("Hello " + name);
        } else{
            resp.sendRedirect("index.html");
        }
    }
}
