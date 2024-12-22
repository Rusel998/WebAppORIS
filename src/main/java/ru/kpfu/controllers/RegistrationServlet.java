package ru.kpfu.controllers;

import ru.kpfu.dto.RegisterDto;
import ru.kpfu.models.User;
import ru.kpfu.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email =   request.getParameter("email");
        String password = request.getParameter("password");

        RegisterDto registerDto = new RegisterDto(username, email, password);


        // Проверяем, не существует ли пользователь с таким email
        if (userService.findByEmail(registerDto.getEmail()).isPresent()) {
            // Пользователь уже существует
            request.setAttribute("error", "User with this email already exists.");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }

        // Преобразуем DTO в сущность
        User user = userService.convertFormDtoToUser(registerDto);
        userService.addUser(user);

        response.sendRedirect(getServletContext().getContextPath() + "/login");
    }
}
