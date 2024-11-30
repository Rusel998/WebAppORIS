package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import ru.kpfu.mapper.RowMapper;
import ru.kpfu.models.User;
import ru.kpfu.repositories.UserRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final DataSource dataSource;
    private final RowMapper<User> userRowMapper;
    @Override
    public void save(String username, String email, String password) {
        System.out.println("Метод save() вызван с параметрами:");
        System.out.println("username: " + username);
        System.out.println("email: " + email);
        System.out.println("password: " + password);
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try (Connection connection = dataSource.getConnection() ){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, email, password) VALUES (?, ?, ?)");

            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, hashedPassword);

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public User validateUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("password");
                    if (BCrypt.checkpw(password, hashedPassword)) {
                        return userRowMapper.mapRow(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to validate user", e);
        }
        return null;
    }



}
