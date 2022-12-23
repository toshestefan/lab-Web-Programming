package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.beans.MutablePropertyValues;

import java.util.List;

public interface ManufacturerService {
    public List<Manufacturer> findAll();

    Manufacturer save(String name, String country,String address);
}
