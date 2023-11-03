package sa.system.Midniyompan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sa.system.Midniyompan.entity.Customer;
import sa.system.Midniyompan.entity.PurchaseOrder;
import sa.system.Midniyompan.model.AddCartRequest;
import sa.system.Midniyompan.service.CustomerService;
import sa.system.Midniyompan.service.FormPOService;
import sa.system.Midniyompan.service.OrderService;


import java.util.UUID;


@Controller
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderService orderService;
    @Autowired
    private FormPOService formPOService;
    @Autowired
    private CustomerService customerService;


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
    @GetMapping("/cart/FormP0")
    public String viewForm(Model model, UUID orderId){
        model.addAttribute("customer",customerService.listAll());
        model.addAttribute("purchaseOrder",orderService.getById(orderId));
        return "FormPO";
    }
//    @PostMapping("/increment")
//    public String incrementQuantity() {
//        orderService.plus();
//        return "redirect:/";
//    }
//
//    @PostMapping("/decrement")
//    public String decrementQuantity() {
//        cartService.decrementQuantity();
//        return "redirect:/";
//    }
    @PostMapping("/delete")
    public String deleteOrder(@PathVariable UUID item,Model model) {
        orderService.deleteOrder(item);
        model.addAttribute("cart", orderService.getCurrentOrder());
        return "cart";
    }
    @PostMapping
    public String submitOrder(@PathVariable UUID productId,Model model) {
        orderService.submitOrder();
        model.addAttribute("confirmOrder", true);
        return "FormPO";
    }

}

