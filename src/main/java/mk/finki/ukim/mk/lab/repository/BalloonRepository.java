package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {
    private final List<Balloon> baloons=new ArrayList<>(10);


    @PostConstruct
    private void init(){
        baloons.add(new Balloon("Small Balloon","This is a small balloon"));
    }

    public List<Balloon> findAllBalloons(){
        return baloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
        return baloons.stream().
                filter(balloon -> balloon.getName().contains(text) || balloon.getDescription().contains(text))
                .collect(Collectors.toList());
    }
}
