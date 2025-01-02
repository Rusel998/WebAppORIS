package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.models.Interest;
import ru.kpfu.repositories.InterestRepository;
import ru.kpfu.repositories.mapper.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class InterestRepositoryImpl implements InterestRepository {
    private final DataSource dataSource;
    private final RowMapper<Interest> rowMapper;

    private static final String SQL_SELECT_BY_NAME = "SELECT * FROM interests WHERE name = ?";
    private static final String FIND_BY_ID = "SELECT * FROM interests WHERE id = ?";
    private static final String FIND_ALL = "SELECT * FROM interests";
    private static final String SAVE = "INSERT INTO interests (name) VALUES (?)";
    private static final String UPDATE = "UPDATE interests SET name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM interests WHERE id = ?";

    @Override
    public Optional<Interest> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {

            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return Optional.of(rowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding interest by ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Interest> findByName(String name) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement ps = connection.prepareStatement(SQL_SELECT_BY_NAME)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Interest interest = new Interest(
                            rs.getLong("id"),
                            rs.getString("name")
                    );
                    return Optional.of(interest);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding interest by name", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Interest> findAll() {
        List<Interest> interests = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL)) {

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                interests.add(rowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding all interests", e);
        }
        return interests;
    }

    @Override
    public void save(Interest interest) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, interest.getName());

            statement.executeUpdate();
            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                interest.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving interest", e);
        }
    }

    @Override
    public boolean update(Interest interest) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE)) {

            statement.setString(1, interest.getName());
            statement.setLong(2, interest.getId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating interest", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE)) {

            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting interest", e);
        }
    }
}
