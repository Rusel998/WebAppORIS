package ru.kpfu.services.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.models.Interest;
import ru.kpfu.repositories.InterestRepository;
import ru.kpfu.services.InterestService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class InterestServiceImpl implements InterestService {
    private final InterestRepository interestRepository;

    @Override
    public Optional<Interest> findById(Long id) {
        return interestRepository.findById(id);
    }

    @Override
    public List<Interest> findAll() {
        return interestRepository.findAll();
    }

    @Override
    public void save(Interest interest) {
        interestRepository.save(interest);
    }

    @Override
    public boolean update(Interest interest) {
        return interestRepository.update(interest);
    }

    @Override
    public boolean delete(Long id) {
        return interestRepository.delete(id);
    }

    @Override
    public boolean addInterestByAdmin(String name) {
        Optional<Interest> existing = interestRepository.findByName(name);
        if (existing.isPresent()) {
            return false;
        } else {
            interestRepository.save(new Interest(null, name));
            return true;
        }
    }
}
