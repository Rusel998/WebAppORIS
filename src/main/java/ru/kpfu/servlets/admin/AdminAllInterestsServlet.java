package ru.kpfu.servlets.admin;

import ru.kpfu.models.Interest;
import ru.kpfu.services.InterestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/allInterests")
public class AdminAllInterestsServlet extends HttpServlet {

    private InterestService interestService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.interestService = (InterestService) getServletContext().getAttribute("interestService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Interest> allInterests = interestService.findAll();
            request.setAttribute("allInterests", allInterests);
            request.getRequestDispatcher("/WEB-INF/views/admin/admin_all_interests.jsp").forward(request, response);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

