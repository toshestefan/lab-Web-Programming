package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Order;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderRepository {
    private final List<Order> orders = new ArrayList<Order>();

    @PostConstruct
    private void init(){
        orders.add(new Order("yellow","big","Stefan Tosic","Kumanovo", 206032L));
    }
    public Order addOrder(String balloonColor, String ballonSize, String clientName, String address,Long id){
        Order order=new Order(balloonColor, ballonSize, clientName, address, id);
        orders.add(order);
        return order;
    }


    public List<Order> listAll() {
        return orders;
    }
}
