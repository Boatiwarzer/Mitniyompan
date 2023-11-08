package sa.system.Midniyompan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sa.system.Midniyompan.common.Status;
import sa.system.Midniyompan.service.FormPOService;
import sa.system.Midniyompan.service.OrderService;


import java.util.UUID;


@Controller
@RequestMapping("/staff/orders")
public class StaffOrderController {


    @Autowired
    private OrderService orderService;
    @Autowired
    private FormPOService formPOService;

    @GetMapping
    public String getAllOrders(Model model) {
        Status process = Status.InProcess;

        model.addAttribute("orders", formPOService.getAllStatus(process));
        return "order-all";
    }
    @GetMapping("/{orderId}")
    public String getAllOrders(@PathVariable UUID orderId, Model model) {
        model.addAttribute("order", formPOService.getOneById(orderId));
        return "order-view";
    }


    @PostMapping("/{orderId}/finish")
    public String finishOrder(@PathVariable UUID orderId, Model model) {
        formPOService.finishOrder(orderId);
        return "redirect:/staff/orders";
    }

}

