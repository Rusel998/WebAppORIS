package ru.kpfu.controllers;

import ru.kpfu.models.PersonalForm;
import ru.kpfu.services.PersonalFormService;

import javax.naming.SizeLimitExceededException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet("/photo")
public class PhotoServlet extends HttpServlet {
    private PersonalFormService personalFormService;

    @Override
    public void init() throws ServletException {
        super.init();
        personalFormService = (PersonalFormService) getServletContext().getAttribute("personalFormService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formIdParam = request.getParameter("formId");
        if (formIdParam == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing formId");
            return;
        }
        Long formId = Long.parseLong(formIdParam);

        // Ищем анкету
        Optional<PersonalForm> formOpt;
        try {
            formOpt = personalFormService.findById(formId);
        } catch (SizeLimitExceededException e) {
            throw new RuntimeException(e);
        }
        if (!formOpt.isPresent()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        PersonalForm form = formOpt.get();
        if (form.getPhoto() == null) {
            // Нет фото - можно отдать заглушку или 404
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Допустим, у вас только JPEG
        // или вы можете хранить contentType где-то отдельно
        response.setContentType("image/jpeg");
        response.setContentLength(form.getPhoto().length);
        response.getOutputStream().write(form.getPhoto());
    }
}

