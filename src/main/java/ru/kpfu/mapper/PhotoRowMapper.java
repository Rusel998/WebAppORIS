package ru.kpfu.mapper;

import ru.kpfu.models.Photo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PhotoRowMapper implements RowMapper<Photo> {

    @Override
    public Photo mapRow(ResultSet resultSet) throws SQLException {
        return Photo.builder()
                .id(resultSet.getLong("id"))
                .profileId(resultSet.getLong("profileId"))
                .photoUrl(resultSet.getString("photoUrl"))
                .uploadDate(resultSet.getObject("uploadDate", LocalDateTime.class))
                .build();
    }
}
