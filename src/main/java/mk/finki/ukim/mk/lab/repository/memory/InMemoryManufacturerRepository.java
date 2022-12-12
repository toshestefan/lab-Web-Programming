package mk.finki.ukim.mk.lab.repository.memory;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class InMemoryManufacturerRepository {

    private final List<Manufacturer> manufacturers=new ArrayList<>();

    @PostConstruct
    public void init(){
    }

    public List<Manufacturer> findAll(){
        return manufacturers;
    }

    public Optional<Manufacturer> findById(Long id){
        return manufacturers.stream().filter(x-> x.getId().equals(id)).findFirst();
    }

    public Optional<Manufacturer> findByName(String name){
        return manufacturers.stream().filter(x-> x.getName().equals(name)).findFirst();
    }




}
