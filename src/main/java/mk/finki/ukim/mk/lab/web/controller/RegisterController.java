package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String register(@RequestParam(required = false) String error){
        return "register";
    }

    @PostMapping("/confirm")
    public String confirm(@RequestParam String username,
                          @RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam String birthdate,
                          @RequestParam String password){
        System.out.println(birthdate);
        authService.register(username, name, surname,LocalDate.parse(birthdate, DateTimeFormatter.ISO_LOCAL_DATE), password);
        return "redirect:/login";
    }

}
