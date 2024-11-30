package ru.kpfu.repositories;

import ru.kpfu.models.User;

import java.sql.SQLException;

public interface UserRepository extends CRUDRepository<User, Long>{
    
    User validateUser(String email, String password) throws SQLException;

}
