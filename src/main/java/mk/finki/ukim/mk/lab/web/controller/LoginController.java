package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.exception.IncorrectPasswordException;
import mk.finki.ukim.mk.lab.exception.UserNotFoundException;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.AuthService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }


    @GetMapping
    public String login(@RequestParam(required = false) String error, @NotNull Model model) {

        model.addAttribute("bodyContent","login");
        return "master-template";
    }

    @PostMapping
    public String confirm(@RequestParam String username,
                          @RequestParam String password,
                          HttpServletRequest req){
        try {
            Optional<User> user = authService.login(username, password);
            if (user.isPresent()) {
                req.getSession().setAttribute("user", username);
                return "redirect:/balloons";
            }
        } catch (IncorrectPasswordException e){
            return "redirect:/login?error=IncorrectPassword";
        } catch (UserNotFoundException e){
            return "redirect:/login?error=UserNotFound";
        }
        return "redirect:/login";
    }
}
