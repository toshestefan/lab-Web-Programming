package mk.finki.ukim.mk.lab.repository.memory;

import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryUserRepository {
    private final List<User> users=new ArrayList<>();

    @PostConstruct
    protected void init(){
    }
}
