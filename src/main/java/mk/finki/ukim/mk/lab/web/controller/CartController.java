package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.CartService;
import mk.finki.ukim.mk.lab.service.OrderService;
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

    public CartController(CartService cartService, OrderService orderService) {
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping
    public String carts(HttpServletRequest req, Model model){
        User user= (User) req.getSession().getAttribute("user");
        model.addAttribute("carts",cartService.selectCartsByUser(user));
        return "carts";
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
        return "orders";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
    public String add(HttpServletRequest req){
        User user= (User) req.getSession().getAttribute("user");
        ShoppingCart cart =new ShoppingCart(user);
        cartService.save(cart);
        return "redirect:/carts";
    }

}
