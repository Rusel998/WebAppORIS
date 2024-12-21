package ru.kpfu.services;

import ru.kpfu.models.Rating;

import java.util.List;
import java.util.Optional;

public interface RatingService {
    Optional<Rating> findById(Long id);
    List<Rating> findAll();
    void save(Rating rating);
    boolean update(Rating rating);
    boolean delete(Long id);

    double getAverageRatingForUser(Long ratedUserId);
}
