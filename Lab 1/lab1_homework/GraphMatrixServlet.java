package com.example.lab1_homework;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/GraphMatrixServlet")
public class GraphMatrixServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logRequestDetails(request);

        int numVertices = Integer.parseInt(request.getParameter("numVertices"));
        int numEdges = Integer.parseInt(request.getParameter("numEdges"));

        int[][] adjMatrix = generateRandomMatrix(numVertices, numEdges);

        response.setContentType("text/html");
        PrintWriter out = getPrintWriter(response, numVertices, adjMatrix);

        out.close();


    }

    private static PrintWriter getPrintWriter(HttpServletResponse response, int numVertices, int[][] adjMatrix) throws IOException {
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Adjacency Matrix</title></head><body>");
        out.println("<h1>Adjacency Matrix</h1>");
        out.println("<table border='1'>");

        for (int i = 0; i < numVertices; i++) {
            out.println("<tr>");
            for (int j = 0; j < numVertices; j++) {
                out.println("<td>" + adjMatrix[i][j] + "</td>");
            }
            out.println("</tr>");
        }
        out.println("</table>");
        out.println("</body></html>");
        return out;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private void logRequestDetails(HttpServletRequest request) {
        String method = request.getMethod();
        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");
        String acceptLanguage = request.getHeader("Accept-Language");
        String numVertices = request.getParameter("numVertices");
        String numEdges = request.getParameter("numEdges");


        getServletContext().log(method);
        getServletContext().log(ipAddress);
        getServletContext().log(userAgent);
        getServletContext().log(acceptLanguage);
        getServletContext().log(numVertices);
        getServletContext().log(numEdges);

        String logFilePath = "/home/stefana-ciudin/Desktop/java/lab1/src/main/java/com/example/lab1_homework/request_log.txt";

        try (FileWriter fw = new FileWriter(logFilePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println("Method: " + method);
            out.println("ipAddress: " + ipAddress);
            out.println("User agent: " + userAgent);
            out.println("Accept language: " + acceptLanguage);
            out.println(numVertices);
            out.println(numEdges);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int[][] generateRandomMatrix(int numVertices, int numEdges) {
        int[][] matrix = new int[numVertices][numVertices];
        Random random = new Random();

        // maximum possible edges in an undirected graph without self-loops is (n * (n-1)) / 2
        int maxEdges = (numVertices * (numVertices - 1)) / 2;

        if (numEdges > maxEdges) {
            numEdges = maxEdges;
        }

        int edgesAdded = 0;

        while (edgesAdded < numEdges) {
            int from = random.nextInt(numVertices);
            int to = random.nextInt(numVertices);


            if (from != to && matrix[from][to] == 0) {
                matrix[from][to] = 1;
                matrix[to][from] = 1;
                edgesAdded++;
            }
        }
        return matrix;
    }

}
