package mk.finki.ukim.mk.lab.service.implementation;

import mk.finki.ukim.mk.lab.exception.BalloonNotFoundException;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.repository.BalloonRepository;
import mk.finki.ukim.mk.lab.repository.ManufacturerRepository;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.exception.ManufacturerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {
    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;

    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAllBalloons();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String text) {
        return balloonRepository.findAllByNameOrDescription(text);
    }

    @Override
    public Optional<Balloon> edit(Long id, String name, String description, Long manufacturer) throws ManufacturerNotFoundException, BalloonNotFoundException {
        Manufacturer manufacturer1=manufacturerRepository.findById(manufacturer).orElseThrow(ManufacturerNotFoundException::new);
        Balloon balloon=balloonRepository.findById(id).orElseThrow(BalloonNotFoundException::new);
        balloon.setName(name);
        balloon.setDescription(description);
        balloon.setManufacturer(manufacturer1);
        return Optional.of(balloon);
    }

    @Override
    public Optional<Balloon> save(String name, String description, Long manufacturer) throws ManufacturerNotFoundException {
        Manufacturer manufacturer1=manufacturerRepository.findById(manufacturer).orElseThrow(ManufacturerNotFoundException::new);

        return balloonRepository.save(name,description,manufacturer1);
    }

    @Override
    public boolean deleteById(Long id) {
        return balloonRepository.deleteById(id);
    }

    @Override
    public Optional<Balloon> findById(Long id) {
        return balloonRepository.findById(id);
    }
}
