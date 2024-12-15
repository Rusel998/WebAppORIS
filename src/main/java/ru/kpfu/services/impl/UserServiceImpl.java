package ru.kpfu.services.impl;

import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import ru.kpfu.models.User;
import ru.kpfu.repositories.UserRepository;
import ru.kpfu.services.UserService;

import javax.naming.SizeLimitExceededException;
import java.util.Optional;

@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public void addUser(User user) {
        // Хэшируем пароль при добавлении нового пользователя
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        // Если роль не установлена, по умолчанию "MEMBER"
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("MEMBER");
        }
        userRepository.save(user);
    }

    @Override
    public Optional<User> validateUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (BCrypt.checkpw(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Long id) throws SizeLimitExceededException {
        return userRepository.findById(id);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepository.delete(id);
    }

}
