package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.mapper.RowMapper;
import ru.kpfu.models.Photo;
import ru.kpfu.repositories.PhotoRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PhotoRepositoryImpl implements PhotoRepository {
    private final DataSource dataSource;
    private final RowMapper<Photo> photoRowMapper;

    private final static String DELETE = "DELETE FROM photo WHERE id = ?";
    private final static String SAVE = "INSERT INTO photo (profileid, photourl, uploadDate) VALUES (?, ?, ?)";
    private final static String FIND_ALL = "SELECT * FROM photo";
    private final static String FIND_BY_ID = "SELECT * FROM photo WHERE id = ?";
    private final static String UPDATE = "UPDATE photo SET photoUrl = ?, uploadDate = ? WHERE id = ?";

    @Override
    public Optional<Photo> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(photoRowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void save(Photo photo) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            statement.setLong(1, photo.getProfileId());
            statement.setString(2, photo.getPhotoUrl());
            statement.setObject(3, photo.getUploadDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Photo> findAll() {
        List<Photo> photos = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                photos.add(photoRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return photos;
    }

    @Override
    public boolean update(Photo photo) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, photo.getPhotoUrl());
            preparedStatement.setObject(2, photo.getUploadDate());
            preparedStatement.setLong(3, photo.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
