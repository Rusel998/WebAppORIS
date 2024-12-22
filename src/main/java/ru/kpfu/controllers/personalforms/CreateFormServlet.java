package ru.kpfu.controllers.personalforms;

import ru.kpfu.models.PersonalForm;
import ru.kpfu.models.User;
import ru.kpfu.services.PersonalFormService;
import ru.kpfu.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

@WebServlet("/personal-form-create")
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
        // Отображаем JSP с формой для создания анкеты
        request.getRequestDispatcher("/WEB-INF/views/create-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем текущего пользователя по email из сессии
        String email = (String) request.getSession().getAttribute("email");
        if (email == null) {
            response.sendRedirect(getServletContext().getContextPath() + "/login");
            return;
        }

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

        // Считываем поля формы
        String bio = request.getParameter("bio");
        String ageParam = request.getParameter("age");
        String birthdateParam = request.getParameter("birthdate"); // в формате yyyy-mm-dd
        String gender = request.getParameter("gender");

        int age = Integer.parseInt(ageParam);
        Date birthdate = Date.valueOf(birthdateParam);

        PersonalForm form = PersonalForm.builder()
                .userId(userId)
                .bio(bio)
                .age(age)
                .birthdate(birthdate)
                .gender(gender)
                .build();

        personalFormService.save(form);

        response.sendRedirect(getServletContext().getContextPath() + "/profile");
    }
}


