package com.example.lab6.ejb;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
public class LoggingInterceptor {

    @AroundInvoke
    public Object logMethod(InvocationContext ctx) throws Exception {
        long startTime = System.currentTimeMillis();

        try {
            System.out.println("Entering method: " + ctx.getMethod().getName());
            return ctx.proceed();
        } finally {
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Exiting method: " + ctx.getMethod().getName() + ". Time taken: " + elapsedTime + " ms");
        }
    }
}
