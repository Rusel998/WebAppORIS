package ru.kpfu.servlets;

import ru.kpfu.services.SecurityServiceInterface;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private SecurityServiceInterface security;

    @Override
    public void init() throws ServletException {
        super.init();
        security = (SecurityServiceInterface) getServletContext().getAttribute("securityService");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        security.save(username, email, password);
        response.sendRedirect(getServletContext().getContextPath() + "/profile");
    }
}
