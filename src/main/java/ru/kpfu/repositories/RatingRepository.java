package ru.kpfu.repositories;

import ru.kpfu.models.Rating;
import java.util.Optional;

public interface RatingRepository extends CRUDRepository<Rating, Long> {

    Optional<Rating> findById(Long id);

    double getAverageRatingForUser(Long ratedUserId);
}
