package ru.kpfu.controllers;

import ru.kpfu.security.SecurityService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private SecurityService security;
    @Override
    public void init() throws ServletException {
        super.init();
        security = (SecurityService) getServletContext().getAttribute("securityService");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        if (password != null && email != null) {
            try {
                if (security.signIn(request, email, password)){
                    response.sendRedirect(getServletContext().getContextPath() + "/profile");
                    return;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        request.setAttribute("email", request.getParameter("email"));
        response.sendRedirect(getServletContext().getContextPath() + "/profile");
    }
}
