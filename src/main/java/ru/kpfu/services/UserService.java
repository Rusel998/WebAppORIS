package ru.kpfu.services;

import ru.kpfu.dto.UserDto;
import ru.kpfu.models.User;

import javax.naming.SizeLimitExceededException;
import java.util.Optional;

public interface UserService {

    void addUser(User user);

    Optional<User> validateUser(String email, String password);

    Optional<User> getUserById(Long id) throws SizeLimitExceededException;

    boolean deleteUser(Long id);

    boolean updateUser(User user);

    Optional<User> findByEmail(String email);

    User convertUserDtoToUser(UserDto userDto);
}
