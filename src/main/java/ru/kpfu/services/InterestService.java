package ru.kpfu.services;

import ru.kpfu.models.Interest;

import java.util.List;
import java.util.Optional;

public interface InterestService {
    Optional<Interest> findById(Long id);
    List<Interest> findAll();
    void save(Interest interest);
    boolean update(Interest interest);
    boolean delete(Long id);
}
