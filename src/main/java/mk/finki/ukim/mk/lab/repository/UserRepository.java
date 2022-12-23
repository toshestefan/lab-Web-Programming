package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<Object> findByUsername(String s);
}
