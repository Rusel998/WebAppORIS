package ru.kpfu.services;

import ru.kpfu.models.Interest;

import java.util.List;

public interface UserInterestsService {
    List<Interest> getUserInterests(Long userId);
    void setUserInterests(Long userId, List<Long> interestIds);
}
