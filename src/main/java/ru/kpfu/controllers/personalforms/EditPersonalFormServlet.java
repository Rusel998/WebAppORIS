package ru.kpfu.controllers.personalforms;

import ru.kpfu.models.PersonalForm;
import ru.kpfu.services.PersonalFormService;
import ru.kpfu.services.UserService;

import javax.naming.SizeLimitExceededException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

@WebServlet("/personal-form-edit")
public class EditPersonalFormServlet extends HttpServlet {
    private PersonalFormService personalFormService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        personalFormService = (PersonalFormService) getServletContext().getAttribute("personalFormService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null) {
            response.sendRedirect(getServletContext().getContextPath() + "/profile");
            return;
        }

        Long formId = Long.parseLong(idParam);
        Optional<PersonalForm> formOpt;
        try {
            formOpt = personalFormService.findById(formId);
        } catch (SizeLimitExceededException e) {
            throw new RuntimeException(e);
        }

        if (!formOpt.isPresent()) {
            response.sendRedirect(getServletContext().getContextPath() + "/profile");
            return;
        }

        PersonalForm form = formOpt.get();
        request.setAttribute("form", form);
        request.getRequestDispatcher("/WEB-INF/views/edit_personal_form.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formIdParam = request.getParameter("id");
        if (formIdParam == null) {
            response.sendRedirect(getServletContext().getContextPath() + "/profile");
            return;
        }

        Long formId = Long.parseLong(formIdParam);

        // Поля из формы
        String bio = request.getParameter("bio");
        int age = Integer.parseInt(request.getParameter("age"));
        Date birthdate = Date.valueOf(request.getParameter("birthdate"));
        String gender = request.getParameter("gender");

        Optional<PersonalForm> formOpt;
        try {
            formOpt = personalFormService.findById(formId);
        } catch (SizeLimitExceededException e) {
            throw new RuntimeException(e);
        }
        if (!formOpt.isPresent()) {
            response.sendRedirect(getServletContext().getContextPath() + "/profile");
            return;
        }

        PersonalForm form = formOpt.get();
        form.setBio(bio);
        form.setAge(age);
        form.setBirthdate(birthdate);
        form.setGender(gender);

        personalFormService.update(form);

        response.sendRedirect(getServletContext().getContextPath() + "/profile");
    }
}
