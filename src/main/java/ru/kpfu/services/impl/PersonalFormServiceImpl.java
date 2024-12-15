package ru.kpfu.services.impl;

import lombok.RequiredArgsConstructor;
import ru.kpfu.models.PersonalForm;
import ru.kpfu.repositories.PersonalFormRepository;
import ru.kpfu.services.PersonalFormService;

import javax.naming.SizeLimitExceededException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class PersonalFormServiceImpl implements PersonalFormService {
    private final PersonalFormRepository personalFormRepository;

    @Override
    public List<PersonalForm> getAllForms() {
        return personalFormRepository.findAll();
    }

    @Override
    public Optional<PersonalForm> getFormById(Long id) throws SizeLimitExceededException {
        return personalFormRepository.findById(id);
    }

    @Override
    public void createForm(PersonalForm personalForm, Long userId) throws Exception {
        Optional<PersonalForm> existingForm = personalFormRepository.findById(userId);
        if (existingForm.isPresent()) {
            throw new Exception("You already have a personal form!");
        }
        personalForm.setUserId(userId);
        personalFormRepository.save(personalForm);
    }

    @Override
    public boolean updateForm(PersonalForm personalForm) {
        return personalFormRepository.update(personalForm);
    }

    @Override
    public boolean deleteForm(Long id) {
        return personalFormRepository.delete(id);
    }
}
