package mk.finki.ukim.mk.lab.repository.memory;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryOrderRepository {
    private final List<Order> orders = new ArrayList<>();

    @PostConstruct
    private void init(){
    }
    public Order addOrder(String balloonColor, String balloonSize){
        Order order=new Order();
        orders.add(order);
        return order;
    }


    public List<Order> listAll() {
        return orders;
    }
}
