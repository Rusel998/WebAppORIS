package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.models.PersonalForm;
import ru.kpfu.repositories.mapper.RowMapper;
import ru.kpfu.repositories.PersonalFormRepository;

import javax.naming.SizeLimitExceededException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PersonalFormRepositoryImpl implements PersonalFormRepository {

    private final DataSource dataSource;
    private final RowMapper<PersonalForm> profileRowMapper;

    private final static String FIND_ALL_BY_INTEREST_ID = "SELECT pf.* FROM personalform pf " +
            "JOIN user_interests ui ON ui.userid = pf.userid " +
            "WHERE ui.interestid = ?";
    private final static String FIND_USER_BY_ID =  "SELECT * FROM personalform WHERE userid = ?";
    private final static String FIND_BY_ID = "SELECT * FROM personalform WHERE id = ?";
    private final static String FIND_ALL = "SELECT * FROM personalform";
    private final static String SAVE = "INSERT INTO personalform (userid, bio, age, birthdate, gender) VALUES (?, ?, ?, ?, ?)";
    private final static String UPDATE = "UPDATE personalform SET bio = ?, age = ?, birthdate = ?, gender = ? WHERE id = ?";
    private final static String DELETE = "DELETE FROM personalform WHERE id = ?";

    @Override
    public Optional<PersonalForm> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                PersonalForm personalForm = profileRowMapper.mapRow(rs);
                return Optional.of(personalForm);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding personal form by ID", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<PersonalForm> findUserById(Long id) throws SizeLimitExceededException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_ID)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                PersonalForm personalForm = profileRowMapper.mapRow(resultSet);
                return Optional.of(personalForm);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<PersonalForm> findAllByInterestId(Long interestId) {
        List<PersonalForm> forms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_BY_INTEREST_ID)) {
            preparedStatement.setLong(1, interestId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                forms.add(profileRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding personal forms by interestId", e);
        }
        return forms;
    }


    @Override
    public List<PersonalForm> findAll() {
        List<PersonalForm> personalForms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                PersonalForm personalForm = profileRowMapper.mapRow(rs);
                personalForms.add(personalForm);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while finding all personal forms", e);
        }
        return personalForms;
    }

    @Override
    public void save(PersonalForm personalForm) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            statement.setLong(1, personalForm.getUserId());
            statement.setString(2, personalForm.getBio());
            statement.setInt(3, personalForm.getAge());
            statement.setDate(4, new java.sql.Date(personalForm.getBirthdate().getTime()));
            statement.setString(5, personalForm.getGender());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean update(PersonalForm personalForm) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, personalForm.getBio());
            preparedStatement.setInt(2, personalForm.getAge());
            preparedStatement.setDate(3, (Date) personalForm.getBirthdate());
            preparedStatement.setString(4, personalForm.getGender());
            preparedStatement.setLong(5, personalForm.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error while updating personal form", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {

            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting personal form", e);
        }
    }
}

