package ru.kpfu.mapper;

import ru.kpfu.models.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileRowMapper implements RowMapper<Profile> {
    @Override
    public Profile mapRow(ResultSet resultSet) throws SQLException {
        return Profile.builder()
                .id(resultSet.getLong("id"))
                .userId(resultSet.getLong("userId"))
                .bio(resultSet.getString("bio"))
                .age(resultSet.getInt("age"))
                .gender(resultSet.getString("gender"))
                .build();
    }
}
