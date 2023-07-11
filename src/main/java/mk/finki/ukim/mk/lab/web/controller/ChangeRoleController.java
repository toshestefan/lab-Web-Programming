package mk.finki.ukim.mk.lab.web.controller;

import mk.finki.ukim.mk.lab.model.Role;
import mk.finki.ukim.mk.lab.service.AuthService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/role")
public class ChangeRoleController {
    private final UserService userService;

    public ChangeRoleController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String role(@RequestParam(required = false) String error, @NotNull Model model) {

        //model.addAttribute("bodyContent","login");
        return "changeRole";
    }

    @PostMapping
    public String changeRole(@RequestParam String role,
                          HttpServletRequest req, Authentication authentication){
        UserDetails user= (UserDetails) authentication.getPrincipal();
        mk.finki.ukim.mk.lab.model.User user1=userService.findByUsername(user.getUsername());
        user1.setRole(Role.valueOf(role));
        userService.save(user1);
        return "redirect:/balloons";
    }
}
