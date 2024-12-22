package ru.kpfu.controllers.admin;

import ru.kpfu.models.PersonalForm;
import ru.kpfu.services.PersonalFormService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/personal-forms")
public class AdminPersonalFormsServlet extends HttpServlet {

    private PersonalFormService personalFormService;

    @Override
    public void init() throws ServletException {
        super.init();
        personalFormService = (PersonalFormService) getServletContext().getAttribute("personalFormService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PersonalForm> personalForms = personalFormService.findAll();
        request.setAttribute("personalForms", personalForms);
        request.getRequestDispatcher("/WEB-INF/views/admin_personal_forms.jsp").forward(request, response);
    }
}

