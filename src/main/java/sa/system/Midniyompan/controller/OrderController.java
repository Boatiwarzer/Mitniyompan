package sa.system.Midniyompan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sa.system.Midniyompan.entity.Customer;
import sa.system.Midniyompan.entity.PurchaseOrder;
import sa.system.Midniyompan.model.AddCartRequest;
import sa.system.Midniyompan.model.FormRequest;
import sa.system.Midniyompan.model.OrderRequest;
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
                        @ModelAttribute AddCartRequest request){
        orderService.order(productId, request);
        return "redirect:/main";
    }
    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("cart", orderService.getCurrentOrder());
        model.addAttribute("customers",customerService.listAll());
        return "cart";
    }
    @GetMapping("/FormPO")
    public String viewForm(Model model){
        model.addAttribute("purchaseOrder",orderService.getOrder());
        model.addAttribute("pos",orderService.getAllOrders());
        return "FormPO";
    }


    @PostMapping
    public String submitOrder(@ModelAttribute OrderRequest request, Model model) {
        orderService.submitOrder(request);
        model.addAttribute("confirmOrder", true);
        return "redirect:/orders/FormPO";

    }
    @PostMapping("/FormPO")
    public String submitFormPO(@ModelAttribute FormRequest request, Model model) {
        formPOService.createForm(request);
        model.addAttribute("confirmOrder", true);
        return "redirect:/main";
    }


}

