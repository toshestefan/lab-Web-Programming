package mk.finki.ukim.mk.lab.web.controller;


import mk.finki.ukim.mk.lab.exception.BalloonNotFoundException;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import mk.finki.ukim.mk.lab.service.OrderService;
import mk.finki.ukim.mk.lab.service.exception.ManufacturerNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/balloons")
public class BalloonController {


    private final BalloonService balloonService;
    private final OrderService orderService;

    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, OrderService orderService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.orderService = orderService;
        this.manufacturerService = manufacturerService;
    }


    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, @NotNull Model model) {
        model.addAttribute("balloons",balloonService.listAll());
        model.addAttribute("orders",orderService.listAll());
        model.addAttribute("manufacturers",manufacturerService.findAll());
        return "listBalloons";
    }

    @PostMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model){
        if (this.balloonService.findById(id).isPresent()) {
            Balloon balloon = this.balloonService.findById(id).get();
            List<Manufacturer> manufacturers = this.manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("balloon", balloon);
            model.addAttribute("header", "Edit balloon");
            return "add-balloon";
        }
        return "redirect:/balloons?error=ProductNotFound";

    }

    @GetMapping("/add-form")
    public String getEditBalloonPage(Model model){
        List<Manufacturer> manufacturers = this.manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("header", "Add balloon");
        return "add-balloon";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Long manufacturer) throws BalloonNotFoundException, ManufacturerNotFoundException {
        if (id != null) {
            this.balloonService.edit(id, name, description, manufacturer);
        } else {
            this.balloonService.save(name, description, manufacturer);
        }
        return "redirect:/balloons";
    }

    @RequestMapping (value = "/delete/{id}",method = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET})
    public String deleteBalloon(@PathVariable Long id){
        System.out.println(balloonService.deleteById(id));
        return "redirect:/balloons";
    }
}
