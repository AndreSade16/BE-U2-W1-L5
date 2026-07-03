package andreasaderi.L5.services;

import andreasaderi.L5.entities.User;
import andreasaderi.L5.exceptions.UserAlreadyExistsException;
import andreasaderi.L5.exceptions.UserNotFoundException;
import andreasaderi.L5.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(String name, String email) {
        if (userRepository.existsByEmail(email)) throw new UserAlreadyExistsException(email);
        else {
            User newUser = new User(name, email);
            userRepository.save(newUser);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(String userId) {
        Optional<User> result = userRepository.findById(UUID.fromString(userId));
        return result.orElseThrow(() -> new UserNotFoundException(userId));
    }
}
