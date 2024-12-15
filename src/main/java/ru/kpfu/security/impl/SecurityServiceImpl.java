package ru.kpfu.security.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.models.User;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.security.SecurityService;
import ru.kpfu.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private UserService userService;

    @Override
    public Map<String, Object> getUser(HttpServletRequest req) {
        if (isSigned(req)) {
            Map<String, Object> user = new HashMap<>();
            user.put("username", req.getSession().getAttribute("username"));
            return user;
        }
        return null;
    }

    @Override
    public boolean isSigned(HttpServletRequest req) {
        return req.getSession().getAttribute("email") != null;
    }

    @Override
    public boolean signIn(HttpServletRequest req, String email, String password) throws SQLException {
        Optional<User> user = userService.validateUser(email, password);
        if (user.isPresent()) {
            req.getSession().setAttribute("username", user.get().getUsername());
            req.getSession().setAttribute("email", user.get().getEmail());
            return true;
        }
        return false;
    }
}




