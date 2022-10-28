package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.User;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users=new ArrayList<>();

    @PostConstruct
    protected void init(){
        users.add(new User("toshe","Stefan","Tosic","be"));
    }
}
