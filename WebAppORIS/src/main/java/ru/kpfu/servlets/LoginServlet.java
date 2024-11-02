package ru.kpfu.servlets;

import ru.kpfu.services.SecurityServiceInterface;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private SecurityServiceInterface security;
    @Override
    public void init() throws ServletException {
        super.init();
        security = (SecurityServiceInterface) getServletContext().getAttribute("securityService");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
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
        getServletContext().getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}
