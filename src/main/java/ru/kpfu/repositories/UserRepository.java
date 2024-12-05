package ru.kpfu.repositories;

import ru.kpfu.models.User;

import javax.naming.SizeLimitExceededException;
import java.sql.SQLException;
import java.util.Optional;

public interface UserRepository extends CRUDRepository<User, Long>{

    Optional<User> findById(Long id) throws SizeLimitExceededException;
    
    User validateUser(String email, String password) throws SQLException;

}
