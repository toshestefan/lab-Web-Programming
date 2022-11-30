package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.model.Order;
import mk.finki.ukim.mk.lab.repository.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.ManufacturerRepository;
import mk.finki.ukim.mk.lab.repository.OrderRepository;
import mk.finki.ukim.mk.lab.service.SearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SearchServiceImpl implements SearchService {
    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final OrderRepository orderRepository;

    public SearchServiceImpl(BalloonRepository balloonRepository,
                             ManufacturerRepository manufacturerRepository,
                             OrderRepository orderRepository)
    {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<String> searchByString(String text) {
        List<String> list=new ArrayList<>();
        searchBalloons(text, list);
        searchManufacturers(text, list);
        searchOrders(text, list);
        return list;
    }

    private void searchOrders(String text, List<String> list) {
        for (Order order : orderRepository.listAll()){
            if (order.toString().contains(text)){
                list.add(order.toString());
            }
        }
    }

    private void searchManufacturers(String text, List<String> list) {
        for (Manufacturer manufacturer: manufacturerRepository.findAll()){
            if (manufacturer.toString().contains(text)){
                list.add(manufacturer.toString());
            }
        }
    }

    private void searchBalloons(String text, List<String> list) {
        for (Balloon balloon : balloonRepository.findAllBalloons()){
            if (balloon.toString().contains(text)){
                list.add(balloon.toString());
            }
        }
    }

}
