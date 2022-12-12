package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.exception.IncorrectPasswordException;
import mk.finki.ukim.mk.lab.exception.UserNotFoundException;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String username, String name, String surname, LocalDate dateOfBirth, String password) {
        System.out.println(username);
        userRepository.save(new User(username,name,surname,dateOfBirth,password));
    }

    @Override
    public Optional<User> login(String username, String password) throws IncorrectPasswordException, UserNotFoundException {
        Optional<User> user= userRepository.findById(username);
        if (user.isEmpty()){
            throw new UserNotFoundException();
        }
        if (!user.get().getPassword().equals(password)){
            throw new IncorrectPasswordException();
        }
        return user;
    }
}
