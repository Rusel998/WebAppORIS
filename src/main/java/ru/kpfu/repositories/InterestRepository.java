package ru.kpfu.repositories;

import ru.kpfu.models.Interest;

import java.util.Optional;

public interface InterestRepository extends CRUDRepository<Interest, Long> {

    Optional<Interest> findById(Long id);

    Optional<Interest> findByName(String name);
}
