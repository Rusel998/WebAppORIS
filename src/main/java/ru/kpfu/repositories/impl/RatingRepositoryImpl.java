package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.repositories.mapper.RowMapper;
import ru.kpfu.models.Rating;
import ru.kpfu.repositories.RatingRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class RatingRepositoryImpl implements RatingRepository {

    private final DataSource dataSource;
    private final RowMapper<Rating> ratingRowMapper;

    private final static String AVERAGE_RATING = "SELECT AVG(rating) as avg_rating FROM rating WHERE ratedUserId = ?";
    private final static String DELETE = "DELETE FROM rating WHERE id = ?";
    private final static String SAVE = "INSERT INTO rating (userId, ratedUserId, rating, date) VALUES (?, ?, ?, ?)";
    private final static String FIND_ALL = "SELECT * FROM rating";
    private final static String FIND_BY_ID = "SELECT * FROM rating WHERE id = ?";
    private final static String UPDATE = "UPDATE rating SET rating = ?, date = ? WHERE id = ?";

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
    public double getAverageRatingForUser(Long ratedUserId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(AVERAGE_RATING)) {
            st.setLong(1, ratedUserId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getDouble("avg_rating");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error calculating average rating", e);
        }
        return 0.0;
    }



    @Override
    public void save(Rating type) {
        try (Connection connection = dataSource.getConnection() ){
            PreparedStatement statement = connection.prepareStatement(SAVE);

            statement.setLong(1, type.getUserId());
            statement.setLong(2, type.getRatedUserId());
            statement.setInt(3, type.getRating());
            statement.setObject(4, type.getDate());

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
            preparedStatement.setObject(2, type.getDate());
            preparedStatement.setLong(3, type.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
