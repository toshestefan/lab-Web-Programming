package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.repository.CartRepository;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public synchronized Order placeOrder(String balloonColor, String balloonSize,String cart)
    {
        Optional<ShoppingCart> cart1=cartRepository.findById((long) Integer.parseInt(cart));
        return orderRepository.save(new Order(balloonColor,balloonSize,cart1.get()));
    }


    @Override
    public List<Order> listAll() {
        return orderRepository.findAll();
    }


    @Override
    public List<Order> findAllByCart(ShoppingCart cart){
        return orderRepository.findAllByCart(cart);
    }
}
