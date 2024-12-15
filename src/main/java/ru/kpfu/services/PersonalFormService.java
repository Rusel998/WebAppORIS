package ru.kpfu.services;

import ru.kpfu.models.PersonalForm;

import javax.naming.SizeLimitExceededException;
import java.util.List;
import java.util.Optional;

public interface PersonalFormService {

    List<PersonalForm> getAllForms();

    Optional<PersonalForm> getFormById(Long id) throws SizeLimitExceededException;

    void createForm(PersonalForm form, Long userId) throws Exception;

    boolean updateForm(PersonalForm personalForm);

    boolean deleteForm(Long id);
}
