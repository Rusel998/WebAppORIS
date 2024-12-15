package ru.kpfu.services.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.models.PersonalForm;
import ru.kpfu.repositories.PersonalFormRepository;
import ru.kpfu.services.PersonalFormService;

import javax.naming.SizeLimitExceededException;
import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PersonalFormServiceImpl implements PersonalFormService {
    private final PersonalFormRepository personalFormRepository;

    @Override
    public Optional<PersonalForm> findById(Long id) throws SizeLimitExceededException {
        return personalFormRepository.findById(id);
    }

    @Override
    public Optional<PersonalForm> findUserById(Long userId) throws SizeLimitExceededException {
        return personalFormRepository.findUserById(userId);
    }

    @Override
    public List<PersonalForm> findAll() {
        return personalFormRepository.findAll();
    }

    @Override
    public void save(PersonalForm personalForm) {
        personalFormRepository.save(personalForm);
    }

    @Override
    public boolean update(PersonalForm personalForm) {
        return personalFormRepository.update(personalForm);
    }

    @Override
    public boolean delete(Long id) {
        return personalFormRepository.delete(id);
    }

    @Override
    public List<PersonalForm> findAllByInterestId(Long interestId) {
        return personalFormRepository.findAllByInterestId(interestId);
    }
}