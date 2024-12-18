package ru.kpfu.repositories;

import ru.kpfu.models.PersonalForm;

import javax.naming.SizeLimitExceededException;
import java.util.List;
import java.util.Optional;

public interface PersonalFormRepository extends CRUDRepository<PersonalForm, Long> {
    Optional<PersonalForm> findById(Long id) throws SizeLimitExceededException;
    Optional<PersonalForm> findUserById(Long id) throws SizeLimitExceededException;
    List<PersonalForm> findAllByInterestId(Long interestId);
}
