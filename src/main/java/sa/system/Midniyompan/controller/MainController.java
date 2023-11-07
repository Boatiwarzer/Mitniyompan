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
import sa.system.Midniyompan.model.AddProductRequest;
import sa.system.Midniyompan.model.ProductRequest;
import sa.system.Midniyompan.service.CategoryService;
import sa.system.Midniyompan.service.ProductService;

import java.util.List;
import java.util.UUID;

@RequestMapping("/main")
@Controller
public class MainController {


    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getAllProduct(Model model, @RequestParam(name = "keyword", required = false, defaultValue = "") String keyword) {
        List<Product> listProducts = productService.listAll(keyword);
        model.addAttribute("products", listProducts);
        model.addAttribute("keyword", keyword);
        return "product-all";
    }
    @GetMapping("/{id}")
    public String getOneProduct(@PathVariable UUID id, Model model) {
        Product product = productService.getOneById(id);
        model.addAttribute("product", product);
        return "product-view";
    }
    @GetMapping("/add")
    public String getProductForm(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());

        return "product-add";
    }
    @GetMapping("/{id}/edit")
    public String getProductEditForm(@PathVariable UUID id,Model model) {
        Product product = productService.getOneById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories",categoryService.getAllCategories());
        return "product-edit";
    }
    @PostMapping("/add")
    public String createProduct(@ModelAttribute ProductRequest product,@RequestParam("file")MultipartFile image) {
        productService.createProduct(product,image);
        return "redirect:/main";
    }
    @PostMapping("/{id}/edit")
    public String editProduct(@PathVariable UUID id,@ModelAttribute ProductRequest product,@RequestParam("file")MultipartFile image) {
        productService.editProduct(product,image,id);

        return "redirect:/main/" + id ;
    }
    @GetMapping("/{id}/addRemain")
    public String getProductPlusDelete(@PathVariable UUID id,Model model){
        model.addAttribute("product",productService.getOneById(id));
        return"product-plusDelete";
    }
    @PostMapping("/{id}/addRemain")
    public String addProductPlusDelete(@PathVariable UUID id, @ModelAttribute AddProductRequest request){
        productService.addProductPlusDelete(request,id);
        return"redirect:/main/{id}/addRemain";
    }
}


