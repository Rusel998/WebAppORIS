package ru.kpfu.services.security.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.models.User;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.services.security.SecurityService;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private UserRepository userRepository;

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
        // Используем обновленный метод validateUser
        User user = userRepository.validateUser(email, password);

        if (user != null) {
            // Сохраняем информацию о пользователе в сессию
            req.getSession().setAttribute("email", user.getEmail());
            req.getSession().setAttribute("username", user.getUsername());
            req.getSession().setAttribute("userId", user.getId()); // Можно сохранить и ID пользователя
            return true;
        }

        return false; // Возвращаем false, если пользователь не прошел проверку
    }
}




