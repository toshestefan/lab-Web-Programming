package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.exception.IncorrectPasswordException;
import mk.finki.ukim.mk.lab.exception.UserNotFoundException;
import mk.finki.ukim.mk.lab.model.User;

import java.time.LocalDate;
import java.util.Optional;

public interface AuthService {
    public void register(String username, String name, String surname, LocalDate dateOfBirth, String password);
    public Optional<User> login(String username, String password) throws IncorrectPasswordException, UserNotFoundException;
}
