package ru.kpfu.servlets.admin;

import ru.kpfu.services.InterestService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/addInterest")
public class AdminAddInterestServlet extends HttpServlet {

    private InterestService interestService;

    @Override
    public void init() throws ServletException {
        super.init();
        this.interestService = (InterestService) getServletContext().getAttribute("interestService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/admin/admin_add_interest.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String interestName = request.getParameter("interestName");
            if (interestName == null || interestName.trim().isEmpty()) {
                request.setAttribute("errorMessage", "Название интереса не может быть пустым!");
                request.getRequestDispatcher("/WEB-INF/views/admin/admin_add_interest.jsp").forward(request, response);
                return;
            }

            boolean added = interestService.addInterestByAdmin(interestName.trim());
            if (!added) {
                request.setAttribute("errorMessage", "Такой интерес уже есть в базе!");
                request.getRequestDispatcher("/WEB-INF/views/admin/admin_add_interest.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/allInterests");
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
