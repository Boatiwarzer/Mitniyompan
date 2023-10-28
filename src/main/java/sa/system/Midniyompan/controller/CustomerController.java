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
@RequestMapping("/customer-add")
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;


    @PostMapping("/customer-add")
    public String createCustomer(@ModelAttribute CustomerRequest customer){
        customerService.createCustomer(customer);
        return "redirect:/main";
    }
    @GetMapping
    public String getCustomerAll(Model model) {
        model.addAttribute("customers", customerService.listAll());
        return "customer-add";
    }
}
