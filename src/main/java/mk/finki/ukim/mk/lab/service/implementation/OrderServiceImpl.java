package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import mk.finki.ukim.mk.lab.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public Order placeOrder(String balloonColor, String ballonSize, String clientName, String address, Long id) {
        return orderRepository.addOrder(balloonColor,ballonSize,clientName,address,id);
    }


    @Override
    public List<Order> listAll() {
        return orderRepository.listAll();
    }
}
