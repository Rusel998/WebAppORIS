package ru.kpfu.servlets.admin;

import ru.kpfu.services.InterestService;
import ru.kpfu.services.UserInterestsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/deleteInterest")
public class AdminDeleteInterestServlet extends HttpServlet {

    private InterestService interestService;
    private UserInterestsService userInterestsService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.interestService = (InterestService) getServletContext().getAttribute("interestService");
        this.userInterestsService = (UserInterestsService) getServletContext().getAttribute("userInterestsService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String interestIdParam = request.getParameter("interestId");
            if (interestIdParam != null) {
                Long interestId = Long.valueOf(interestIdParam);
                userInterestsService.deleteUserInterests(interestId);
                interestService.delete(interestId);
            }
            response.sendRedirect(request.getContextPath() + "/admin/allInterests");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

