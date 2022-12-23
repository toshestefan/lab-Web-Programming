package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.exception.InvalidUsernameOrPasswordException;
import mk.finki.ukim.mk.lab.exception.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.exception.UsernameAlreadyExistsException;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;

public interface UserService extends UserDetailsService {

    public User register(String username, String name, String surname, LocalDate dateOfBirth, String password, Role role) throws UsernameAlreadyExistsException, InvalidUsernameOrPasswordException;
    public User findByUsername(String username);
}

