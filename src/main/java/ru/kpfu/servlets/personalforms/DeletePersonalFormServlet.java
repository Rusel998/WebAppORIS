package ru.kpfu.servlets.personalforms;

import ru.kpfu.services.PersonalFormService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/personal-form-delete")
public class DeletePersonalFormServlet extends HttpServlet {
    private PersonalFormService personalFormService;

    @Override
    public void init() throws ServletException {
        super.init();
        personalFormService = (PersonalFormService) getServletContext().getAttribute("personalFormService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String formIdParam = request.getParameter("id");
            if (formIdParam == null) {
                response.sendRedirect(getServletContext().getContextPath() + "/profile");
                return;
            }

            Long formId = Long.parseLong(formIdParam);
            personalFormService.delete(formId);

            response.sendRedirect(getServletContext().getContextPath() + "/my-form");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
