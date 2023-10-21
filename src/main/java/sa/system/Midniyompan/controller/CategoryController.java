package sa.system.Midniyompan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sa.system.Midniyompan.model.CategoryRequest;
import sa.system.Midniyompan.service.CategoryService;


@Controller
@RequestMapping("/categories")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping("/add")
    public String getCategoryForm(Model model) {
        return "category-add";
    }


    @PostMapping("/add")
    public String createCategory(@ModelAttribute CategoryRequest request,
                                 Model model) {
        categoryService.createCategory(request);
        return "redirect:/main";
    }

}

