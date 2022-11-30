package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {
    private final List<Balloon> baloons=new ArrayList<>(10);
    private final ManufacturerRepository manufacturerRepository;

    public BalloonRepository(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @PostConstruct
    private void init(){
        if (manufacturerRepository.findByName("Balunco").isPresent()) {
            baloons.add(new Balloon("Small Balloon", "This is a small balloon", manufacturerRepository.findByName("Balunco").get()));
        }
    }

    public List<Balloon> findAllBalloons(){
        return baloons;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
        return baloons.stream().
                filter(balloon -> balloon.getName().contains(text) || balloon.getDescription().contains(text))
                .collect(Collectors.toList());
    }

    public Optional<Balloon> save(String name, String description, Manufacturer manufacturer) {
        baloons.removeIf(x->x.getName().equals(name));
        Balloon balloon=new Balloon(name,description,manufacturer);
        baloons.add(balloon);
        return Optional.of(balloon);
    }

    public Optional<Balloon> findById(Long id) {
        return baloons.stream().filter(b->b.getId().equals(id)).findFirst();
    }

    public boolean deleteById(Long id) {
        return baloons.removeIf(balloon -> balloon.getId().equals(id));
    }
}
