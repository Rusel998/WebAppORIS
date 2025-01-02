package ru.kpfu.services;

import ru.kpfu.models.PersonalForm;

import javax.naming.SizeLimitExceededException;
import java.util.List;
import java.util.Optional;

public interface PersonalFormService {
    Optional<PersonalForm> findById(Long id) throws SizeLimitExceededException;
    Optional<PersonalForm> findUserById(Long userId) throws SizeLimitExceededException;
    List<PersonalForm> findAll();
    void save(PersonalForm personalForm);
    boolean update(PersonalForm personalForm);
    boolean delete(Long id);
    List<PersonalForm> findAllByInterestId(Long interestId);
}
