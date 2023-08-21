package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {
}
