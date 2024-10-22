package com.example.lab2.homework;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/*")
public class ResponseDecoratorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Wrap the response
        CharResponseWrapper wrappedResponse = new CharResponseWrapper((HttpServletResponse) response);

        // Pass the request down the filter chain
        chain.doFilter(request, wrappedResponse);

        // Get prelude and coda from application scope
        ServletContext context = request.getServletContext();
        String prelude = (String) context.getAttribute("prelude");
        String coda = (String) context.getAttribute("coda");

        // Modify the response content
        String originalContent = wrappedResponse.toString();
        String decoratedContent = (prelude != null ? prelude : "") + originalContent + (coda != null ? coda : "");

        // Write the decorated content to the response
        PrintWriter out = response.getWriter();
        out.write(decoratedContent);
    }

    @Override
    public void destroy() {
        // Cleanup code if necessary
    }

    // Custom wrapper class to capture the response output
    public static class CharResponseWrapper extends HttpServletResponseWrapper {
        private final CharArrayWriter charWriter = new CharArrayWriter();

        public CharResponseWrapper(HttpServletResponse response) {
            super(response);
        }

        @Override
        public PrintWriter getWriter() {
            return new PrintWriter(charWriter);
        }

        @Override
        public String toString() {
            return charWriter.toString();
        }
    }
}
