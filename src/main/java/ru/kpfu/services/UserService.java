package ru.kpfu.services;


import ru.kpfu.dto.RegisterDto;
import ru.kpfu.dto.UserDto;
import ru.kpfu.models.User;
import javax.naming.SizeLimitExceededException;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    void addUser(User user);

    UserDto validateUser(String email, String password);

    Optional<User> getUserById(Long id) throws SizeLimitExceededException;

    boolean deleteUser(Long id);

    boolean updateUser(User user);

    Optional<User> findByEmail(String email);

    User convertFormDtoToUser(RegisterDto registerDto);

    UserDto convertUserToDto(User user);

}
