package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface CartService {


    List<ShoppingCart> selectCartsByUser(User user);

    public void deleteCartById(Long id);

    public List<ShoppingCart> findAll();


    public Optional<ShoppingCart> selectCartById(Long id);

    void save(ShoppingCart cart);
}
