package ru.kpfu.servlets.admin;

import ru.kpfu.services.PersonalFormService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/delete-personalform")
public class AdminDeletePersonalFormServlet extends HttpServlet {

    private PersonalFormService personalFormService;

    @Override
    public void init() throws ServletException {
        super.init();
        personalFormService = (PersonalFormService) getServletContext().getAttribute("personalFormService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String personalFormIdParam = request.getParameter("id");
            if (personalFormIdParam != null) {
                Long personalFormId = Long.parseLong(personalFormIdParam);
                personalFormService.delete(personalFormId);
            }
            response.sendRedirect(getServletContext().getContextPath() + "/admin/personal-forms");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
