package ru.kpfu.servlets.rating;

import ru.kpfu.models.Rating;
import ru.kpfu.models.User;
import ru.kpfu.services.RatingService;
import ru.kpfu.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

@WebServlet("/rating-create")
public class RatingCreateServlet extends HttpServlet {
    private UserService userService;
    private RatingService ratingService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
        ratingService = (RatingService) getServletContext().getAttribute("ratingService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ratedUserIdParam = request.getParameter("ratedUserId");
            if (ratedUserIdParam == null) {
                response.sendRedirect(getServletContext().getContextPath() + "/personal-forms");
                return;
            }
            request.setAttribute("ratedUserId", ratedUserIdParam);
            request.getRequestDispatcher("/WEB-INF/views/create_rating.jsp").forward(request, response);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String ratedUserIdParam = request.getParameter("ratedUserId");
            String ratingParam = request.getParameter("rating");
            String email = (String) request.getSession().getAttribute("email");

            Optional<User> currentUserOpt = userService.findByEmail(email);
            if (!currentUserOpt.isPresent()) {
                response.sendRedirect(getServletContext().getContextPath() + "/login");
                return;
            }

            Long userId = currentUserOpt.get().getId();
            Long ratedUserId = Long.parseLong(ratedUserIdParam);
            int ratingValue = Integer.parseInt(ratingParam);

            Optional<Rating> existingRatingOpt = ratingService.findAll().stream()
                    .filter(r -> r.getUserId().equals(userId) && r.getRatedUserId().equals(ratedUserId))
                    .findFirst();

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            if (existingRatingOpt.isPresent()) {
                Rating existingRating = existingRatingOpt.get();
                existingRating.setRating(ratingValue);
                existingRating.setDate(timestamp);
                ratingService.update(existingRating);
            } else {
                Rating newRating = Rating.builder()
                        .userId(userId)
                        .ratedUserId(ratedUserId)
                        .rating(ratingValue)
                        .date(timestamp)
                        .build();
                ratingService.save(newRating);
            }

            response.sendRedirect(getServletContext().getContextPath() + "/personal-forms");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
