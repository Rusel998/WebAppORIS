package ru.kpfu.controllers.complaint;

import ru.kpfu.models.Complaint;
import ru.kpfu.models.User;
import ru.kpfu.services.ComplaintService;
import ru.kpfu.services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Optional;

@WebServlet("/complaint-create")
public class CreateComplaintServlet extends HttpServlet {
    private UserService userService;
    private ComplaintService complaintService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = (UserService) getServletContext().getAttribute("userService");
        complaintService = (ComplaintService) getServletContext().getAttribute("complaintService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String offenderIdParam = request.getParameter("id");
        if (offenderIdParam == null) {
            response.sendRedirect(getServletContext().getContextPath() + "/personal-forms");
            return;
        }
        request.setAttribute("offenderId", offenderIdParam);
        request.getRequestDispatcher("/WEB-INF/views/create_complaint.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String offenderIdParam = request.getParameter("offenderId");
        String reason = request.getParameter("reason");
        String email = (String) request.getSession().getAttribute("email");

        Optional<User> complainantOpt = userService.findByEmail(email);
        if (!complainantOpt.isPresent()) {
            response.sendRedirect(getServletContext().getContextPath() + "/login");
            return;
        }

        Long complainantId = complainantOpt.get().getId();
        Long offenderId = Long.parseLong(offenderIdParam);

        Complaint complaint = Complaint.builder()
                .complainantId(complainantId)
                .offenderId(offenderId)
                .reason(reason)
                .datetime(new Timestamp(System.currentTimeMillis()))
                .build();

        complaintService.save(complaint);

        // Перенаправляем назад на список анкет
        response.sendRedirect(getServletContext().getContextPath() + "/personal-forms");
    }
}

