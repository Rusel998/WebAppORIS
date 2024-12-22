package ru.kpfu.repositories;

import ru.kpfu.models.Complaint;

import javax.naming.SizeLimitExceededException;
import java.util.Optional;

public interface ComplaintRepository extends CRUDRepository<Complaint, Long> {
    Optional<Complaint> findById(Long id) throws SizeLimitExceededException;
    boolean updateStatus(Long id, String status);
}
