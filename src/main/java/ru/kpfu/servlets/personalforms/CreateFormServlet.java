package ru.kpfu.servlets.personalforms;

import ru.kpfu.models.PersonalForm;
import ru.kpfu.models.User;
import ru.kpfu.services.PersonalFormService;
import ru.kpfu.services.UserService;

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

@WebServlet("/personal-form-create")
@MultipartConfig
public class CreateFormServlet extends HttpServlet {
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
        request.getRequestDispatcher("/WEB-INF/views/create-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = (String) request.getSession().getAttribute("email");
            Optional<User> currentUserOpt;
            try {
                currentUserOpt = userService.findByEmail(email);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (!currentUserOpt.isPresent()) {
                response.sendRedirect(getServletContext().getContextPath() + "/login");
                return;
            }
            User currentUser = currentUserOpt.get();
            Long userId = currentUser.getId();

            String bio = request.getParameter("bio");
            String ageParam = request.getParameter("age");
            String gender = request.getParameter("gender");
            int age = Integer.parseInt(ageParam);

            Part photoPart = request.getPart("photo");
            byte[] photoBytes = null;
            if (photoPart != null && photoPart.getSize() > 0) {
                try (InputStream is = photoPart.getInputStream()) {
                    photoBytes = is.readAllBytes();
                }
            }

            PersonalForm form = PersonalForm.builder()
                    .userId(userId)
                    .bio(bio)
                    .age(age)
                    .gender(gender)
                    .photo(photoBytes)
                    .build();

            personalFormService.save(form);

            response.sendRedirect(getServletContext().getContextPath() + "/my-form");
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}



