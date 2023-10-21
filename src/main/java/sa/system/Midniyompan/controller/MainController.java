package sa.system.Midniyompan.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sa.system.Midniyompan.entity.Category;
import sa.system.Midniyompan.entity.Product;
import sa.system.Midniyompan.model.ProductRequest;
import sa.system.Midniyompan.service.CategoryService;
import sa.system.Midniyompan.service.ProductService;

import java.util.List;
import java.util.UUID;


@Controller
public class MainController {


    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/main")
    public String getSearchProduct(Model model, @Param("keyword") String keyword) {
        List<Product> listProducts = productService.listAll(keyword);
        model.addAttribute("products", listProducts);
        model.addAttribute("keyword", keyword);

        return "product-all";
    }
    @GetMapping
    public String getAllProduct(Model model, @Param("keyword") String keyword) {
        List<Product> listProducts = productService.listAll(keyword);
        model.addAttribute("products", listProducts);

        return "product-all";
    }

    @GetMapping("/{id}")
    public String getOneProduct(@PathVariable UUID id, Model model) {
        Product product = productService.getOneById(id);
        model.addAttribute("product", product);
        return "product-view";
    }


    @GetMapping("/main/add")
    public String getProductForm(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());

        return "product-add";
    }

    @PostMapping("/main/add")
    public String createProduct(@ModelAttribute ProductRequest product,@RequestParam("file")MultipartFile image) {
        productService.createProduct(product,image);
        return "redirect:/main";
    }
}


