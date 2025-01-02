package ru.kpfu.servlets.personalforms;

import ru.kpfu.models.Interest;
import ru.kpfu.models.PersonalForm;
import ru.kpfu.models.User;
import ru.kpfu.services.InterestService;
import ru.kpfu.services.PersonalFormService;
import ru.kpfu.services.RatingService;
import ru.kpfu.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@WebServlet("/personal-forms")
public class AllPersonalFormsServlet extends HttpServlet {
    private PersonalFormService personalFormService;
    private UserService userService;
    private InterestService interestService;
    private RatingService ratingService;

    @Override
    public void init() throws ServletException {
        super.init();
        personalFormService = (PersonalFormService) getServletContext().getAttribute("personalFormService");
        userService = (UserService) getServletContext().getAttribute("userService");
        interestService = (InterestService) getServletContext().getAttribute("interestService");
        ratingService = (RatingService) getServletContext().getAttribute("ratingService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = (String) request.getSession().getAttribute("email");
            Long currentUserId;
            if (email != null) {
                Optional<User> uOpt = userService.findByEmail(email);
                currentUserId = uOpt.map(User::getId).orElse(null);
            } else {
                currentUserId = null;
            }

            String interestIdParam = request.getParameter("interestId");
            List<PersonalForm> allForms;
            if (interestIdParam != null && !interestIdParam.isEmpty()) {
                Long interestId = Long.parseLong(interestIdParam);
                allForms = personalFormService.findAllByInterestId(interestId);
            } else {
                allForms = personalFormService.findAll();
            }

            if (currentUserId != null) {
                allForms.removeIf(form -> form.getUserId().equals(currentUserId));
            }

            Map<Long, User> usersMap = new HashMap<>();
            Map<Long, Double> avgRatingsMap = new HashMap<>();
            Map<Long, Integer> roundedRatingsMap = new HashMap<>();

            for (PersonalForm form : allForms) {
                try {
                    Optional<User> userOpt = userService.getUserById(form.getUserId());
                    userOpt.ifPresent(user -> usersMap.put(form.getUserId(), user));
                    double avg = ratingService.getAverageRatingForUser(form.getUserId());
                    int rounded = (int)Math.round(avg);

                    avgRatingsMap.put(form.getUserId(), avg);
                    roundedRatingsMap.put(form.getUserId(), rounded);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            List<Interest> allInterests = interestService.findAll();

            request.setAttribute("allForms", allForms);
            request.setAttribute("usersMap", usersMap);
            request.setAttribute("avgRatingsMap", avgRatingsMap);
            request.setAttribute("roundedRatingsMap", roundedRatingsMap);
            request.setAttribute("allInterests", allInterests);
            request.getRequestDispatcher("/WEB-INF/views/all_personal_forms.jsp").forward(request, response);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

