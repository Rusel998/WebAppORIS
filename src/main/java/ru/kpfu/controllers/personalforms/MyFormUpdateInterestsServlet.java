package ru.kpfu.controllers.personalforms;

import ru.kpfu.models.User;
import ru.kpfu.services.UserInterestsService;
import ru.kpfu.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/my-form-interests")
public class MyFormUpdateInterestsServlet extends HttpServlet {
    private UserService userService;
    private UserInterestsService userInterestsService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
        userInterestsService = (UserInterestsService) getServletContext().getAttribute("userInterestsService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = (String) request.getSession().getAttribute("email");

        Optional<User> userOpt = userService.findByEmail(email);
        Long userId = userOpt.get().getId();

        // Собираем интересы из формы (передаются как чекбоксы)
        String[] interestIdsStr = request.getParameterValues("interestId");
        List<Long> interestIds = new ArrayList<>();
        if (interestIdsStr != null) {
            for (String s : interestIdsStr) {
                interestIds.add(Long.parseLong(s));
            }
        }

        userInterestsService.setUserInterests(userId, interestIds);

        response.sendRedirect(getServletContext().getContextPath() + "/my-form");
    }
}
