package ru.kpfu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String role = (String) req.getSession().getAttribute("role");
        if (!"admin".equalsIgnoreCase(role)) {
            res.sendRedirect(req.getContextPath() + "/profile");
            return;
        }

        chain.doFilter(request, response);
    }
}

