package ru.kpfu.mapper;

import ru.kpfu.models.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ActivityRowMapper implements RowMapper<Activity> {

    @Override
    public Activity mapRow(ResultSet resultSet) throws SQLException {
        return Activity.builder()
                .id(resultSet.getLong("id"))
                .userId(resultSet.getLong("userId"))
                .profileViews(resultSet.getInt("profileViews"))
                .lastLogin(resultSet.getObject("lastLogin", LocalDateTime.class))
                .build();
    }
}
