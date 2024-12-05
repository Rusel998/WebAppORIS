package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.mapper.RowMapper;
import ru.kpfu.models.Rating;
import ru.kpfu.repositories.RatingRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class RatingRepositoryImpl implements RatingRepository {

    private final DataSource dataSource;
    private final RowMapper<Rating> ratingRowMapper;

    private final static String DELETE = "DELETE FROM rating WHERE id = ?";
    private final static String SAVE = "INSERT INTO rating (userId, ratedUserId, rating, comment) VALUES (?, ?, ?, ?)";
    private final static String FIND_ALL = "SELECT * FROM rating";
    private final static String FIND_BY_ID = "SELECT * FROM rating WHERE id = ?";
    private final static String UPDATE = "UPDATE rating SET rating = ?, comment = ? WHERE id = ?";

    @Override
    public Optional<Rating> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)){

            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Rating rating = ratingRowMapper.mapRow(rs);
                    return Optional.of(rating);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }


    @Override
    public void save(Rating type) {
        try (Connection connection = dataSource.getConnection() ){
            PreparedStatement statement = connection.prepareStatement(SAVE);

            statement.setLong(1, type.getUserId());
            statement.setLong(2, type.getRatedUserId());
            statement.setInt(3, type.getRating());
            statement.setString(4, type.getComment());

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
    public List<Rating> findAll() {
        List<Rating> ratings = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Rating rating = ratingRowMapper.mapRow(rs);
                ratings.add(rating);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving users", e);
        }
        return ratings;
    }

    @Override
    public boolean update(Rating type) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {

            preparedStatement.setLong(1, type.getRating());
            preparedStatement.setString(2, type.getComment());
            preparedStatement.setLong(3, type.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
