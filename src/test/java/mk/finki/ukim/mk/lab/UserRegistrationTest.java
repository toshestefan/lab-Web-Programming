package mk.finki.ukim.mk.lab;


import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import mk.finki.ukim.mk.lab.service.implementation.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserServiceImpl userService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        User user=new User("a","a","a", LocalDate.now(),"a");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("a");
        this.userService=Mockito.spy(new UserServiceImpl(userRepository,passwordEncoder));

    }

    @Test
    public void testSuccessfulRegistration(){
        User user=this.userService.register("a","a","a", LocalDate.now(),"a",Role.ROLE_USER);
        Mockito.verify(this.userService).register("a","a","a", LocalDate.now(),"a", Role.ROLE_USER);

        Assert.assertNotNull("User is null",user);
        Assert.assertEquals("username does not match","a",user.getUsername());
        Assert.assertEquals("password does not match","a",user.getPassword());
    }
}
