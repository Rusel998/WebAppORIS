package ru.kpfu.repositories;

import ru.kpfu.models.Interest;

import java.util.List;

public interface UserInterestsRepository {
    List<Interest> findByUserId(Long userId);
    void saveUserInterests(Long userId, List<Long> interestIds);
    void clearUserInterests(Long userId);
    void deleteUserInterests(Long interestId);
}
