package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.exception.InvalidUsernameOrPasswordException;
import mk.finki.ukim.mk.lab.exception.PasswordsDoNotMatchException;
import mk.finki.ukim.mk.lab.exception.UsernameAlreadyExistsException;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return (UserDetails) userRepository.findByUsername(s).orElseThrow(()->new UsernameNotFoundException(s));
    }


    @Override
    public User register(String username, String name, String surname, LocalDate dateOfBirth, String password, Role role) throws UsernameAlreadyExistsException, InvalidUsernameOrPasswordException {
        if (username==null || username.isEmpty()  || password==null || password.isEmpty())
            throw new InvalidUsernameOrPasswordException();
        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);
        User user = new User(username,name,surname,dateOfBirth,passwordEncoder.encode(password),role);
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return (User) userRepository.findByUsername(username).get();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


}

