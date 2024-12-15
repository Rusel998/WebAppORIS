package ru.kpfu.repositories.mapper.Impl;

import ru.kpfu.models.Interest;
import ru.kpfu.repositories.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InterestRowMapper implements RowMapper<Interest> {
    @Override
    public Interest mapRow(ResultSet resultSet) throws SQLException {
        return Interest.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .build();
    }
}
