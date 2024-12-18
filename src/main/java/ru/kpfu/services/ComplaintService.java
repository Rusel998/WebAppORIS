package ru.kpfu.services;

import ru.kpfu.models.Complaint;

import javax.naming.SizeLimitExceededException;
import java.util.List;
import java.util.Optional;

public interface ComplaintService {
    Optional<Complaint> findById(Long id) throws SizeLimitExceededException;
    List<Complaint> findAll();
    void save(Complaint complaint);
    boolean update(Complaint complaint);
    boolean delete(Long id);
}
