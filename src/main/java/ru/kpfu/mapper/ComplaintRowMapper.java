package ru.kpfu.mapper;

import ru.kpfu.models.Complaint;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ComplaintRowMapper implements RowMapper<Complaint>{
    @Override
    public Complaint mapRow(ResultSet resultSet) throws SQLException {
        return Complaint.builder()
                .id(resultSet.getLong("id"))
                .complainantId(resultSet.getLong("complainantId"))
                .offenderId(resultSet.getLong("offenderId"))
                .reason(resultSet.getString("reason"))
                .comment(resultSet.getString("comment"))
                .dateTime(resultSet.getObject("dateTime", LocalDateTime.class))
                .build();
    }
}
