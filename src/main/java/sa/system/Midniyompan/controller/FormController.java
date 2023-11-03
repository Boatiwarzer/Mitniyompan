package sa.system.Midniyompan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import sa.system.Midniyompan.entity.Product;
import sa.system.Midniyompan.service.FormPOService;

import java.util.UUID;

@Controller
@RequestMapping("/FromPO")
public class FormController {
    @Autowired
    FormPOService formPOService;
//    @GetMapping("/{id}")
//    public String getOneProduct(@PathVariable UUID id, Model model) {
//        Product product = productService.getOneById(id);
//        model.addAttribute("product", product);
//        return "product-view";
//    }

}
