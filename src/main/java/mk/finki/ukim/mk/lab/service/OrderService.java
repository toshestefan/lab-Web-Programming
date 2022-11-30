package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
     Order placeOrder(String balloonColor, String balloonSize, String clientName, String address, Long id);

    List<Order> listAll();
}
