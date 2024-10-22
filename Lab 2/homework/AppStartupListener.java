package com.example.lab2.homework;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();

        // Read context init parameters
        String prelude = context.getInitParameter("prelude");
        String coda = context.getInitParameter("coda");

        // Store them as application-scoped attributes
        context.setAttribute("prelude", prelude);
        context.setAttribute("coda", coda);

        System.out.println("AppStartupListener initialized: Prelude = " + prelude + ", Coda = " + coda);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Cleanup code if necessary
    }
}

