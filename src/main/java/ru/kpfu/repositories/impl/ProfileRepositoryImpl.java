package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.mapper.RowMapper;
import ru.kpfu.models.Profile;
import ru.kpfu.models.Rating;
import ru.kpfu.repositories.ProfileRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProfileRepositoryImpl implements ProfileRepository {
    private final DataSource dataSource;
    private final RowMapper<Profile> profileRowMapper;

    private final static String DELETE = "DELETE FROM profile WHERE id = ?";
    private final static String SAVE = "INSERT INTO profile (userid, bio, age, gender) VALUES (?, ?, ?, ?)";
    private final static String FIND_ALL = "SELECT * FROM profile";
    private final static String FIND_BY_ID = "SELECT * FROM profile WHERE id = ?";
    private final static String UPDATE = "UPDATE profile SET bio = ?, age = ?, gender = ? WHERE id = ?";

    @Override
    public Optional<Profile> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Profile profile = profileRowMapper.mapRow(rs);
                    return Optional.of(profile);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    @Override
    public void save(Profile type) {
        try (Connection connection = dataSource.getConnection() ){
            PreparedStatement statement = connection.prepareStatement(SAVE);
            statement.setLong(1, type.getUserId());
            statement.setString(2, type.getBio());
            statement.setInt(3, type.getAge());
            statement.setString(4, type.getGender());

            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)){
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Profile> findAll() {
        List<Profile> profiles = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Profile profile = profileRowMapper.mapRow(rs);
                profiles.add(profile);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving users", e);
        }
        return profiles;
    }

    @Override
    public boolean update(Profile type) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setString(1, type.getBio());
            preparedStatement.setInt(2, type.getAge());
            preparedStatement.setString(3, type.getGender());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
