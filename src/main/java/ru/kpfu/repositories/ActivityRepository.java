package ru.kpfu.repositories;

import ru.kpfu.models.Activity;

import javax.naming.SizeLimitExceededException;
import java.util.Optional;

public interface ActivityRepository extends CRUDRepository<Activity, Long> {
    Optional<Activity> findById(Long id) throws SizeLimitExceededException;
}
