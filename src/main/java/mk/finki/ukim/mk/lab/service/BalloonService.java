package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.exception.BalloonNotFoundException;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.exception.ManufacturerNotFoundException;

import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String text);

    public Optional<Balloon> edit(Long id, String name, String description, Long manufacturer) throws ManufacturerNotFoundException, BalloonNotFoundException;

    public Optional<Balloon> save(String name, String description, Long manufacturer) throws ManufacturerNotFoundException;

    public void deleteById(Long id);

    public Optional<Balloon> findById(Long id);
}
