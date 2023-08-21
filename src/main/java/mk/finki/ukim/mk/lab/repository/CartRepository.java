package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<ShoppingCart,Long> {

    List<ShoppingCart> findAllByUser(User user);
}
