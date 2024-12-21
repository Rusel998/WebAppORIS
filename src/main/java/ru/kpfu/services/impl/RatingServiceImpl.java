package ru.kpfu.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.models.Rating;
import ru.kpfu.repositories.RatingRepository;
import ru.kpfu.services.RatingService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    @Override
    public Optional<Rating> findById(Long id) {
        return ratingRepository.findById(id);
    }

    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public void save(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public boolean update(Rating rating) {
        return ratingRepository.update(rating);
    }

    @Override
    public boolean delete(Long id) {
        return ratingRepository.delete(id);
    }

    @Override
    public double getAverageRatingForUser(Long ratedUserId) {
        return ratingRepository.getAverageRatingForUser(ratedUserId);
    }
}

