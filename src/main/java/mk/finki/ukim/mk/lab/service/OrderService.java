package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
     Order placeOrder(String balloonColor, String balloonSize,String cart);

     List<Order> findAllByCart(ShoppingCart cart);

    List<Order> listAll();
}
