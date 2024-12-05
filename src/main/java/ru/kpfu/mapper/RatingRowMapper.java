package ru.kpfu.mapper;

import ru.kpfu.models.Rating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingRowMapper implements RowMapper<Rating> {

    @Override
    public Rating mapRow(ResultSet resultSet) throws SQLException {
        return Rating.builder()
                .id(resultSet.getLong("id"))
                .userId(resultSet.getLong("userId"))
                .ratedUserId(resultSet.getLong("ratedUserId"))
                .rating(resultSet.getInt("rating"))
                .comment(resultSet.getString("comment"))
                .build();
    }
}
