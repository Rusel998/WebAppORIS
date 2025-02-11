package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.repositories.mapper.RowMapper;
import ru.kpfu.models.Complaint;
import ru.kpfu.repositories.ComplaintRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ComplaintRepositoryImpl implements ComplaintRepository {
    private final DataSource dataSource;
    private final RowMapper<Complaint> complaintRowMapper;

    private final static String UPDATE_STATUS = "UPDATE complaint SET status = ? WHERE id = ?";
    private final static String DELETE = "DELETE FROM complaint WHERE id = ?";
    private final static String SAVE = "INSERT INTO complaint (complainantId, offenderId, reason, dateTime) VALUES (?, ?, ?, ?)";
    private final static String FIND_ALL = "SELECT * FROM complaint";
    private final static String FIND_BY_ID = "SELECT * FROM complaint WHERE id = ?";
    private final static String UPDATE = "UPDATE complaint SET offenderId = ?, reason = ?, dateTime = ? WHERE id = ?";

    @Override
    public Optional<Complaint> findById(Long id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(complaintRowMapper.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public boolean updateStatus(Long id, String status) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setLong(2, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void save(Complaint complaint) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            statement.setLong(1, complaint.getComplainantId());
            statement.setLong(2, complaint.getOffenderId());
            statement.setString(3, complaint.getReason());
            statement.setObject(4, complaint.getDatetime());
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
    public List<Complaint> findAll() {
        List<Complaint> complaints = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                complaints.add(complaintRowMapper.mapRow(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return complaints;
    }

    @Override
    public boolean update(Complaint complaint) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setLong(1, complaint.getOffenderId());
            preparedStatement.setString(2, complaint.getReason());
            preparedStatement.setObject(3, complaint.getDatetime());
            preparedStatement.setLong(4, complaint.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}