package controller;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Optional: Initialization code can be added here if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false); // Get current session

        // Check if user is logged in (session should not be null)
        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        String loginURI = httpRequest.getContextPath() + "/index.jsp"; // Your login page

        // If user is not logged in, redirect to the login page
        if (!loggedIn && !httpRequest.getRequestURI().equals(loginURI)) {
            httpResponse.sendRedirect(loginURI); // Redirect to login page
        } else {
            // User is logged in, continue to the requested resource
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // Optional: Cleanup code can be added here if needed
    }
}
