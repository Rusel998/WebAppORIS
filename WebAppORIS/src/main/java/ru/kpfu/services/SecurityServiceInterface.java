package ru.kpfu.services;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

public interface SecurityServiceInterface {
    String validateUser(String email, String password) throws SQLException;

    boolean isSigned (HttpServletRequest req);

    Map<String, Object> getUser(HttpServletRequest req);

    boolean signIn(HttpServletRequest req, String email, String password) throws SQLException;

    void save(String username, String email, String password);
}
