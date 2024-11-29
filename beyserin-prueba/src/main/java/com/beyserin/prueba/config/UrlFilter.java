package com.beyserin.prueba.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class UrlFilter extends HttpFilter {
    
    private static final long serialVersionUID = 1L;
    private static final List<String> ALLOWED_URLS = Arrays.asList(
            ApiRoutes.ClIENTES,
            ApiRoutes.ClIENTES + "/*"
    );

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String requestUri = request.getRequestURI();

        if (isUrlAllowed(requestUri)) {
            chain.doFilter(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Recurso no encontrado");
            response.setContentType("text/plain");
            response.setCharacterEncoding("UTF-8");
        }
    }

    private boolean isUrlAllowed(String requestUri) {
        return ALLOWED_URLS.stream().anyMatch(requestUri::startsWith);
    }
}