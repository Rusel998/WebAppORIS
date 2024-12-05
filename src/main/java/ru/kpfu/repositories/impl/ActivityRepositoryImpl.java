package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.mapper.RowMapper;
import ru.kpfu.models.Activity;
import ru.kpfu.repositories.ActivityRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ActivityRepositoryImpl implements ActivityRepository {
    private final DataSource dataSource;
    private final RowMapper<Activity> activityRowMapper;

    private final static String DELETE = "DELETE FROM activity WHERE id = ?";
    private final static String SAVE = "INSERT INTO activity (userid, lastLogin, profileViews) VALUES (?, ?, ?)";
    private final static String FIND_ALL = "SELECT * FROM activity";
    private final static String FIND_BY_ID = "SELECT * FROM activity WHERE id = ?";
    private final static String UPDATE = "UPDATE activity SET lastLogin = ?, profileViews = ? WHERE id = ?";

    @Override
    public Optional<Activity> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(activityRowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void save(Activity activity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            statement.setLong(1, activity.getUserId());
            statement.setObject(2, activity.getLastLogin());
            statement.setInt(3, activity.getProfileViews());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Activity> findAll() {
        List<Activity> activities = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                activities.add(activityRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return activities;
    }

    @Override
    public boolean update(Activity activity) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setObject(1, activity.getLastLogin());
            preparedStatement.setInt(2, activity.getProfileViews());
            preparedStatement.setLong(3, activity.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
