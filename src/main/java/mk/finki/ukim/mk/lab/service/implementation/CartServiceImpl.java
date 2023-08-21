package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.CartRepository;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.CartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<ShoppingCart> selectCartById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<ShoppingCart> selectCartsByUser(User user) {
        return cartRepository.findAllByUser(user);
    }


    @Override
    @Transactional
    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<ShoppingCart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    @Transactional
    public void save(ShoppingCart cart) {
        cartRepository.save(cart);

    }
}
