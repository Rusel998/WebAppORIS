package ru.kpfu.repositories;

import ru.kpfu.models.Profile;

import javax.naming.SizeLimitExceededException;
import java.util.Optional;

public interface ProfileRepository extends CRUDRepository<Profile, Long> {
    Optional<Profile> findById(Long id) throws SizeLimitExceededException;
}
