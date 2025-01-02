package ru.kpfu.servlets.admin;

import ru.kpfu.models.Complaint;
import ru.kpfu.services.ComplaintService;
import ru.kpfu.services.UserService;

import javax.naming.SizeLimitExceededException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/complaints")
public class AdminComplaintsServlet extends HttpServlet {
    private ComplaintService complaintService;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        complaintService = (ComplaintService) getServletContext().getAttribute("complaintService");
        userService = (UserService) getServletContext().getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Complaint> complaints = complaintService.findAll();

        Map<Long, String> userNames = new HashMap<>();
        for (Complaint c : complaints) {
            if (!userNames.containsKey(c.getComplainantId())) {
                try {
                    userService.getUserById(c.getComplainantId()).ifPresent(u -> userNames.put(c.getComplainantId(), u.getUsername()));
                } catch (SizeLimitExceededException e) {
                    throw new RuntimeException(e);
                }
            }
            if (!userNames.containsKey(c.getOffenderId())) {
                try {
                    userService.getUserById(c.getOffenderId()).ifPresent(u -> userNames.put(c.getOffenderId(), u.getUsername()));
                } catch (SizeLimitExceededException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        request.setAttribute("complaints", complaints);
        request.setAttribute("userNames", userNames);

        request.getRequestDispatcher("/WEB-INF/views/admin/admin_complaints.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String complaintIdParam = request.getParameter("id");
            String action = request.getParameter("action");

            if (complaintIdParam != null && action != null) {
                Long complaintId = Long.parseLong(complaintIdParam);
                String status = "pending";
                if ("accepted".equals(action)) {
                    status = "accepted";
                } else if ("declined".equals(action)) {
                    status = "declined";
                }
                complaintService.updateStatus(complaintId, status);
            }

            response.sendRedirect(getServletContext().getContextPath() + "/admin/complaints");
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

