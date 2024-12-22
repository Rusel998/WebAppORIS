package ru.kpfu.services.impl;

import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import ru.kpfu.dto.RegisterDto;
import ru.kpfu.dto.UserDto;
import ru.kpfu.models.User;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.services.UserService;

import javax.naming.SizeLimitExceededException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    @Override
    public UserDto validateUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (BCrypt.checkpw(password, user.getPassword())) {
                return convertUserToDto(user);
            }
        }
        return null;
    }

    @Override
    public Optional<User> getUserById(Long id) throws SizeLimitExceededException {
        return userRepository.findById(id);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepository.delete(id);
    }

    @Override
    public boolean updateUser(User user) {
        return userRepository.update(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User convertFormDtoToUser(RegisterDto registerDto) {
        return new User(
                null,
                registerDto.getUsername(),
                registerDto.getEmail(),
                registerDto.getPassword(),
                null
        );
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}

