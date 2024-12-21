package ru.kpfu.repositories.mapper.Impl;

import ru.kpfu.repositories.mapper.RowMapper;
import ru.kpfu.models.Complaint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ComplaintRowMapper implements RowMapper<Complaint> {
    @Override
    public Complaint mapRow(ResultSet resultSet) throws SQLException {
        return Complaint.builder()
                .id(resultSet.getLong("id"))
                .complainantId(resultSet.getLong("complainantId"))
                .offenderId(resultSet.getLong("offenderId"))
                .reason(resultSet.getString("reason"))
                .datetime(resultSet.getTimestamp("dateTime"))
                .status(resultSet.getString("status"))
                .build();
    }
}
