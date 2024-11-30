package ru.kpfu.services.security;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Map;

public interface SecurityService {

    boolean isSigned (HttpServletRequest req);

    Map<String, Object> getUser(HttpServletRequest req);

    boolean signIn(HttpServletRequest req, String email, String password) throws SQLException;

}
