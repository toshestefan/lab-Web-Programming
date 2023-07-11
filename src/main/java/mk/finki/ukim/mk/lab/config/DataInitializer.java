package mk.finki.ukim.mk.lab.config;

import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.NoSuchElementException;

@Component
public class DataInitializer {
    private final UserService userService;
    private final ManufacturerService manufacturerService;


    public DataInitializer(UserService userService, ManufacturerService manufacturerService) {
        this.userService = userService;
        this.manufacturerService = manufacturerService;
    }

    @PostConstruct
    public void init(){
        manufacturerService.save("a","a","a");
         try {
             userService.findByUsername("a");
         } catch (NoSuchElementException e){
             userService.register("a","a","a",LocalDate.now(),"a",Role.ROLE_ADMIN);
         }
    }
}
