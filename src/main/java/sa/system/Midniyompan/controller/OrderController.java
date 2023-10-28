package sa.system.Midniyompan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sa.system.Midniyompan.model.AddCartRequest;
import sa.system.Midniyompan.service.OrderService;


import java.util.UUID;


@Controller
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @PostMapping("/{productId}")
    public String order(@PathVariable UUID productId,
                        @ModelAttribute AddCartRequest request, Model model){
        orderService.order(productId, request);
        return "redirect:/main";
    }
    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", orderService.getCurrentOrder());
        return "cart";
    }


    @PostMapping
    public String submitOrder(Model model) {
        orderService.submitOrder();
        model.addAttribute("confirmOrder", true);
        return "home";
    }

}

