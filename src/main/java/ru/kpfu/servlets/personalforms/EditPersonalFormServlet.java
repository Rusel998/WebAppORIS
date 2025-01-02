package ru.kpfu.servlets.personalforms;

import ru.kpfu.models.PersonalForm;
import ru.kpfu.services.PersonalFormService;
import javax.naming.SizeLimitExceededException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@WebServlet("/personal-form-edit")
@MultipartConfig
public class EditPersonalFormServlet extends HttpServlet {
    private PersonalFormService personalFormService;

    @Override
    public void init() throws ServletException {
        super.init();
        personalFormService = (PersonalFormService) getServletContext().getAttribute("personalFormService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
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
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            String formIdParam = request.getParameter("id");
            if (formIdParam == null) {
                response.sendRedirect(getServletContext().getContextPath() + "/profile");
                return;
            }
            Long formId = Long.parseLong(formIdParam);

            String bio = request.getParameter("bio");
            int age = Integer.parseInt(request.getParameter("age"));
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
            form.setGender(gender);

            Part photoPart = request.getPart("photo");
            if (photoPart != null && photoPart.getSize() > 0) {
                byte[] newPhotoBytes;
                try (InputStream is = photoPart.getInputStream()) {
                    newPhotoBytes = is.readAllBytes();
                }
                form.setPhoto(newPhotoBytes);
            }

            personalFormService.update(form);

            response.sendRedirect(getServletContext().getContextPath() + "/my-form");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
