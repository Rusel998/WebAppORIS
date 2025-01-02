package ru.kpfu.servlets;

import ru.kpfu.models.User;
import ru.kpfu.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/user-settings")
public class ProfileSettingsServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String userEmail = (String) request.getSession().getAttribute("email");
            Optional<User> userOpt = userService.findByEmail(userEmail);
            if (!userOpt.isPresent()) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            User currentUser = userOpt.get();
            request.setAttribute("currentUser", currentUser);

            request.getRequestDispatcher("/WEB-INF/views/user_settings.jsp").forward(request, response);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getParameter("action");
            if ("cancel".equals(action)) {
                response.sendRedirect(request.getContextPath() + "/profile");
                return;
            }

            String oldEmail = (String) request.getSession().getAttribute("email");

            String newUsername = request.getParameter("username");
            String newEmail = request.getParameter("email");

            if (newUsername == null || newUsername.isEmpty() ||
                    newEmail == null || newEmail.isEmpty()) {
                request.setAttribute("error", "All fields are required!");
                doGet(request, response);
                return;
            }

            Optional<User> currentOpt = userService.findByEmail(oldEmail);
            if (!currentOpt.isPresent()) {
                response.sendRedirect(request.getContextPath() + "/login");
                return;
            }

            User currentUser = currentOpt.get();

            if (!newEmail.equals(oldEmail)) {
                Optional<User> existingOpt = userService.findByEmail(newEmail);
                if (existingOpt.isPresent()) {
                    User existingUser = existingOpt.get();
                    if (!existingUser.getId().equals(currentUser.getId())) {
                        request.setAttribute("error", "Пользователь с таким email уже существует!");
                        request.setAttribute("currentUser", currentUser);
                        request.getRequestDispatcher("/WEB-INF/views/user_settings.jsp").forward(request, response);
                        return;
                    }
                }
            }

            currentUser.setUsername(newUsername);
            currentUser.setEmail(newEmail);

            userService.updateUser(currentUser);

            request.getSession().setAttribute("username", newUsername);
            request.getSession().setAttribute("email", newEmail);

            response.sendRedirect(request.getContextPath() + "/profile");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
