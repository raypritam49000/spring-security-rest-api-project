package com.rest.api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final HttpServletResponse response = (HttpServletResponse) servletResponse;
        final String authHeader = request.getHeader("authorization");

        logger.info("@@@ Inside JwtFilter Auth Header : {} ", authHeader);

        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Missing or invalid Authorization header");
            return;
        }

        final String token = authHeader.substring(7).trim();

        if (!new JwtUtil().isTokenValid(token)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid JWT token");
            return;
        }

        request.setAttribute("authTokenDetails", new JwtUtil().getAuthTokenDetailsFromToken(token));

        filterChain.doFilter(request, response);
    }
}