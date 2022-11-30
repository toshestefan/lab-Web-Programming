package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.xml.transform.sax.SAXResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository
public class ManufacturerRepository {

    private final List<Manufacturer> manufacturers=new ArrayList<>();

    @PostConstruct
    public void init(){
        manufacturers.add(new Manufacturer("Balunco","Macedonia","Kumanovo"));
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
