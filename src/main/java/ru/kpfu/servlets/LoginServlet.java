package ru.kpfu.servlets;

import ru.kpfu.dto.UserDto;
import ru.kpfu.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String password = request.getParameter("password");
            String email = request.getParameter("email");

            if (password != null && email != null) {
                UserDto userDto = userService.validateUser(email, password);
                if (userDto != null) {
                    request.getSession().setAttribute("username", userDto.getUsername());
                    request.getSession().setAttribute("email", userDto.getEmail());
                    request.getSession().setAttribute("role", userDto.getRole());

                    if ("admin".equalsIgnoreCase(userDto.getRole())) {
                        response.sendRedirect(getServletContext().getContextPath() + "/admin/dashboard");
                    }
                    else {
                        response.sendRedirect(getServletContext().getContextPath() + "/profile");
                    }
                    return;
                }
            }

            request.setAttribute("email", request.getParameter("email"));
            response.sendRedirect(getServletContext().getContextPath() + "/login");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
