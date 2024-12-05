package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.mapper.RowMapper;
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

    private final static String DELETE = "DELETE FROM complaint WHERE id = ?";
    private final static String SAVE = "INSERT INTO complaint (complainantId, offenderId, reason, comment, dateTime) VALUES (?, ?, ?, ?, ?)";
    private final static String FIND_ALL = "SELECT * FROM complaint";
    private final static String FIND_BY_ID = "SELECT * FROM complaint WHERE id = ?";
    private final static String UPDATE = "UPDATE complaint SET offenderId = ?, reason = ?, comment = ?, dateTime = ? WHERE id = ?";

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
    public void save(Complaint complaint) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SAVE)) {
            statement.setLong(1, complaint.getComplainantId());
            statement.setLong(2, complaint.getOffenderId());
            statement.setString(3, complaint.getReason());
            statement.setString(4, complaint.getComment());
            statement.setObject(5, complaint.getDateTime());
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
            preparedStatement.setString(3, complaint.getComment());
            preparedStatement.setObject(4, complaint.getDateTime());
            preparedStatement.setLong(5, complaint.getId());
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
