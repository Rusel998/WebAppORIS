package ru.kpfu.services.impl;

import lombok.AllArgsConstructor;
import ru.kpfu.models.Complaint;
import ru.kpfu.repositories.ComplaintRepository;
import ru.kpfu.services.ComplaintService;
import javax.naming.SizeLimitExceededException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class ComplaintServiceImpl implements ComplaintService {
    private final ComplaintRepository complaintRepository;

    @Override
    public Optional<Complaint> findById(Long id) throws SizeLimitExceededException {
        return complaintRepository.findById(id);
    }

    @Override
    public List<Complaint> findAll() {
        return complaintRepository.findAll();
    }

    @Override
    public void save(Complaint complaint) {
        complaintRepository.save(complaint);
    }

    @Override
    public boolean update(Complaint complaint) {
        return complaintRepository.update(complaint);
    }

    @Override
    public boolean delete(Long id) {
        return complaintRepository.delete(id);
    }
}