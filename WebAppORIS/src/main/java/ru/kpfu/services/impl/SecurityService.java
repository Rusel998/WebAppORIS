package ru.kpfu.services.impl;

import ru.kpfu.services.SecurityServiceInterface;
import ru.kpfu.util.DriverManagerDataSource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SecurityService implements SecurityServiceInterface {
    private final DataSource dataSource = new DriverManagerDataSource("org.postgresql.Driver",
            "jdbc:postgresql://localhost:5432/postgres",
            "postgres", "998123");

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
        String username = validateUser(email, password);
        if (username != null) {
            req.getSession().setAttribute("email", email);  // Сохраняем email в сессию
            req.getSession().setAttribute("username", username);  // Сохраняем username в сессию
            return true;
        }
        return false;
    }


    @Override
    public String validateUser(String email, String password) throws SQLException {
        String sql = "select * from users where email = ? and password = ?";
        try (Connection conn = dataSource.getConnection(); PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("username");  // Возвращаем username
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(String username, String email, String password) {
        try (Connection connection = dataSource.getConnection() ){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
