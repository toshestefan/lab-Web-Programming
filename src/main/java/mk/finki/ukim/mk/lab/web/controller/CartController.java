package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.service.CartService;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final OrderService orderService;

    private final UserService userService;



    public CartController(CartService cartService, OrderService orderService, UserService userService) {
        this.cartService = cartService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String carts(HttpServletRequest req, Model model,Authentication authentication){
        //User user= (User) authentication.getPrincipal();
        mk.finki.ukim.mk.lab.model.User user1=userService.findByUsername(authentication.getName());
        model.addAttribute("carts",cartService.selectCartsByUser(user1));
        model.addAttribute("bodyContent","carts");
        return "master-template";
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.DELETE,RequestMethod.GET,RequestMethod.POST})
    public String delete(@PathVariable String id){
        System.out.println(id+" delete");
        cartService.deleteCartById((long) Integer.parseInt(id));
        return "redirect:/carts";
    }

    @RequestMapping(value = "/select/{id}", method = {RequestMethod.GET,RequestMethod.POST})
    public String select(@PathVariable String id,Model model){
        ShoppingCart cart=cartService.selectCartById((long) Integer.parseInt(id)).get();
        model.addAttribute("orders",orderService.findAllByCart(cart));
        model.addAttribute("bodyContent","orders");
        return "master-template";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
    public String add(HttpServletRequest req, Authentication authentication){
        //User user= (User) authentication.getPrincipal();
        mk.finki.ukim.mk.lab.model.User user1=userService.findByUsername(authentication.getName());
        ShoppingCart cart =new ShoppingCart(user1);
        cartService.save(cart);
        return "redirect:/carts";
    }

}
