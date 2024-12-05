package ru.kpfu.repositories;

import ru.kpfu.models.Photo;

import javax.naming.SizeLimitExceededException;
import java.util.Optional;

public interface PhotoRepository extends CRUDRepository<Photo, Long> {
    Optional<Photo> findById(Long id) throws SizeLimitExceededException;
}
