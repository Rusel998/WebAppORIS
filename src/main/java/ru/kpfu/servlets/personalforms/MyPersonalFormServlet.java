package ru.kpfu.servlets.personalforms;

import ru.kpfu.models.Interest;
import ru.kpfu.models.PersonalForm;
import ru.kpfu.models.User;
import ru.kpfu.services.InterestService;
import ru.kpfu.services.PersonalFormService;
import ru.kpfu.services.UserInterestsService;
import ru.kpfu.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/my-form")
public class MyPersonalFormServlet extends HttpServlet {
    private PersonalFormService personalFormService;
    private UserService userService;
    private InterestService interestService;
    private UserInterestsService userInterestsService;

    @Override
    public void init() throws ServletException {
        super.init();
        personalFormService = (PersonalFormService) getServletContext().getAttribute("personalFormService");
        userService = (UserService) getServletContext().getAttribute("userService");
        interestService = (InterestService) getServletContext().getAttribute("interestService");
        userInterestsService = (UserInterestsService) getServletContext().getAttribute("userInterestsService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = (String) request.getSession().getAttribute("email");
            if (email == null) {
                response.sendRedirect(getServletContext().getContextPath() + "/login");
                return;
            }

            Optional<User> userOpt = userService.findByEmail(email);
            if (!userOpt.isPresent()) {
                response.sendRedirect(getServletContext().getContextPath() + "/login");
                return;
            }

            User user = userOpt.get();
            Long userId = user.getId();
            Optional<PersonalForm> formOpt = Optional.empty();
            try {
                formOpt = personalFormService.findUserById(userId);
            } catch (Exception e) {
                e.printStackTrace();
            }

            List<Interest> allInterests = interestService.findAll();
            List<Interest> userInterests = userInterestsService.getUserInterests(userId);

            request.setAttribute("user", user);
            request.setAttribute("form", formOpt.orElse(null));
            request.setAttribute("allInterests", allInterests);
            request.setAttribute("userInterests", userInterests);

            request.getRequestDispatcher("/WEB-INF/views/my_form.jsp").forward(request, response);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
