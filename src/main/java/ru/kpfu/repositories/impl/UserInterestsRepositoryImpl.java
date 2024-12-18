package ru.kpfu.repositories.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.models.Interest;
import ru.kpfu.repositories.UserInterestsRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserInterestsRepositoryImpl implements UserInterestsRepository {
    private final DataSource dataSource;

    private final static String CLEAR = "DELETE FROM user_interests WHERE userid = ?";
    private final static String FIND_BY_USER_ID = "SELECT i.* FROM user_interests ui JOIN interests i ON ui.interestid = i.id WHERE ui.userid = ?";
    private final static String SAVE = "INSERT INTO user_interests (userid, interestid) VALUES (?, ?)";
    @Override
    public List<Interest> findByUserId(Long userId) {
        List<Interest> interests = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(FIND_BY_USER_ID)) {
            st.setLong(1, userId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                interests.add(new Interest(rs.getLong("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return interests;
    }

    @Override
    public void saveUserInterests(Long userId, List<Long> interestIds) {
        // Сначала очистим старые интересы:
        clearUserInterests(userId);

        if (interestIds == null || interestIds.isEmpty()) {
            return;
        }
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(SAVE)) {
            for (Long interestId : interestIds) {
                st.setLong(1, userId);
                st.setLong(2, interestId);
                st.addBatch();
            }
            st.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void clearUserInterests(Long userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement st = connection.prepareStatement(CLEAR)) {
            st.setLong(1, userId);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
