package sa.system.Midniyompan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sa.system.Midniyompan.common.Status;
import sa.system.Midniyompan.model.AddCartRequest;
import sa.system.Midniyompan.model.FormRequest;
import sa.system.Midniyompan.model.OrderRequest;
import sa.system.Midniyompan.model.ReceiptRequest;
import sa.system.Midniyompan.service.CustomerService;
import sa.system.Midniyompan.service.FormPOService;
import sa.system.Midniyompan.service.OrderService;
import sa.system.Midniyompan.service.ReceiptService;


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
    @Autowired
    private ReceiptService receiptService;


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
        return "formPO";
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
    @GetMapping("/order-finished")
    public String getAllOrders(Model model) {
         Status finish = Status.FINISH;
        model.addAttribute("orders", formPOService.getAllStatus(finish));
        return "order-finished";
    }
    @GetMapping("/order-reserve")
    public String getAllOrdersReserve(Model model) {
        model.addAttribute("orders", formPOService.getAll());
        return "order-reserve";
    }
    @GetMapping("/order-finished/{orderId}")
    public String getAllOrders(@PathVariable UUID orderId, Model model) {
        model.addAttribute("order", formPOService.getOneById(orderId));
        return "order-viewFinished";
    }
    @PostMapping("{formPOId}")
    public String createReceipt(@ModelAttribute ReceiptRequest request) {
        receiptService.createReceipt(request);
        return "redirect:/receipt";
    }


    @PostMapping("/order-finished/{orderId}/complete")
    public String finishOrder(@PathVariable UUID orderId, Model model) {
        formPOService.finishOrder(orderId);
        return "redirect:/orders/order-finished/{orderId}";
    }
    @PostMapping("/order-reserve/{orderId}/complete")
    public String finishOrder2(@PathVariable UUID orderId, Model model) {
        formPOService.finishOrder(orderId);
        return "redirect:/orders/order-reserve/{orderId}";
    }


}

