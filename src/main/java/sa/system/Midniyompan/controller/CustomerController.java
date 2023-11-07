package sa.system.Midniyompan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sa.system.Midniyompan.model.AddCartRequest;
import sa.system.Midniyompan.model.CustomerRequest;
import sa.system.Midniyompan.repository.CustomerRepository;
import sa.system.Midniyompan.service.CustomerService;
import sa.system.Midniyompan.service.OrderService;

import java.util.UUID;
@Controller
@RequestMapping("/customer-all")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer-add")
    public String showCustomerForm() {
            // รายการโค้ดที่คุณต้องการเพิ่มเข้ามา
        return "customer-add";
    }
    @GetMapping
    public String showCustomerAll(Model model){
        model.addAttribute("customers",customerService.listAll());
        return "customer-all";
    }

    @PostMapping
    public String createCustomer(@ModelAttribute CustomerRequest customer,Model model) {
        customerService.createCustomer(customer);
        model.addAttribute("customerSuccess",true);
        return "customer-all";
    }
}
