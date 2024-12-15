package ru.kpfu.repositories.mapper.Impl;

import ru.kpfu.models.PersonalForm;
import ru.kpfu.repositories.mapper.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonalFormRowMapper implements RowMapper<PersonalForm> {
    @Override
    public PersonalForm mapRow(ResultSet resultSet) throws SQLException {
        return PersonalForm.builder()
                .id(resultSet.getLong("id"))
                .userId(resultSet.getLong("userId"))
                .bio(resultSet.getString("bio"))
                .age(resultSet.getInt("age"))
                .gender(resultSet.getString("gender"))
                .build();
    }
}
