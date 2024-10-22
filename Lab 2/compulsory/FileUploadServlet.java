package com.example.lab2.compulsory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/FileUploadServlet")
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Validate CAPTCHA
        String captchaAnswer = request.getParameter("captchaAnswer");
        int captchaResult = (Integer) request.getSession().getAttribute("captchaResult");

        if (captchaAnswer != null && Integer.parseInt(captchaAnswer) == captchaResult) {
            // CAPTCHA is correct, process the file

            // Get the uploaded file
            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();

            // Read the file line by line and store in a list
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();

            // Shuffle the lines
            Collections.shuffle(lines);

            // Store the shuffled lines in session
            HttpSession session = request.getSession();
            session.setAttribute("shuffledLines", lines);

            // Redirect to result page
            response.sendRedirect("result.jsp");
        } else {
            // CAPTCHA is incorrect, show an error
            response.getWriter().println("CAPTCHA validation failed. Please go back and try again.");
        }
    }
}