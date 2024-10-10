package com.example.lab1_compulsory;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

// http://localhost:8080/lab1_war_exploded/StringToListServlet
@WebServlet("/StringToListServlet")
public class StringToListServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String inputString = "HelloWorld";

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Character List</title></head><body>");
        out.println("<h1>Characters in the String</h1>");

        out.println("<ol>");
        for (char c : inputString.toCharArray()) {
            out.println("<li>" + c + "</li>");
        }
        out.println("</ol>");
        out.println("</body></html>");

        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }
}