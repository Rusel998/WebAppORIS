package ru.kpfu.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.models.Interest;
import ru.kpfu.repositories.UserInterestsRepository;
import ru.kpfu.services.UserInterestsService;

import java.util.List;

@AllArgsConstructor
public class UserInterestsServiceImpl implements UserInterestsService {
    private final UserInterestsRepository userInterestsRepository;

    @Override
    public List<Interest> getUserInterests(Long userId) {
        return userInterestsRepository.findByUserId(userId);
    }

    @Override
    public void setUserInterests(Long userId, List<Long> interestIds) {
        userInterestsRepository.saveUserInterests(userId, interestIds);
    }

    @Override
    public void deleteUserInterests(Long interestId) {
        userInterestsRepository.deleteUserInterests(interestId);
    }
}

