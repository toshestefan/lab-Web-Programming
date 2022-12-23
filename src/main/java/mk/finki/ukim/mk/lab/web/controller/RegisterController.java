package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.exception.InvalidUsernameOrPasswordException;
import mk.finki.ukim.mk.lab.exception.UsernameAlreadyExistsException;
import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.service.AuthService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService authService;

    public RegisterController(UserService authService) {
        this.authService = authService;
    }

    @GetMapping
    public String register(@RequestParam(required = false) String error, Model model){

        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping
    public String confirm(@RequestParam String username,
                          @RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam String birthdate,
                          @RequestParam String password) throws UsernameAlreadyExistsException, InvalidUsernameOrPasswordException {
        System.out.println(birthdate);
        authService.register(username, name, surname,LocalDate.parse(birthdate, DateTimeFormatter.ISO_LOCAL_DATE), password, Role.ROLE_USER);
        return "redirect:/login";
    }

}
