package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import ru.kpfu.repositories.mapper.RowMapper;
import ru.kpfu.models.User;
import ru.kpfu.repositories.UserRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final DataSource dataSource;
    private final RowMapper<User> userRowMapper;

    private final static String DELETE = "DELETE FROM users WHERE id = ?";
    private final static String VALIDATE = "SELECT * FROM users WHERE email = ?";
    private final static String SAVE = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
    private final static String FIND_ALL = "SELECT * FROM users";
    private final static String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";
    private final static String UPDATE = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";

    @Override
    public Optional<User> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){

            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    User user = userRowMapper.mapRow(rs);
                    return Optional.of(user);
                }
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(VALIDATE)) {

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(userRowMapper.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while validating user", e);
        }
        return Optional.empty();
    }

    @Override
    public void save(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving user", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                User user = userRowMapper.mapRow(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving users", e);
        }
        return users;
    }

    @Override
    public boolean update(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
