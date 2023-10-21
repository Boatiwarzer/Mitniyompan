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
//        @GetMapping("/page/{pageNumber}")
//        public String listByPage(Model model,
//                                 @PathVariable("pageNumber") int currentPage,
//                                 @Param("sortField") String sortField,
//                                 @Param("sortDir") String sortDir,
//                                 @Param("keyword") String keyword) {
//            Page<Product> page = productService.listAll(currentPage, sortField, sortDir, keyword);
//            model.addAttribute("page", page); // เพิ่มผลลัพธ์ Page ไปยัง Model
//            model.addAttribute("categories", categoryService.getAllCategories());
//            return "product-all";
//        }



//        @GetMapping
//        public String getAllProduct(Model model) {
//            model.addAttribute("categories", categoryService.getAllCategories());
//            model.addAttribute("products", productService.getAllProducts());
//
//            return "product-all";
//        }
//        @GetMapping
//        public String getAllProduct(@RequestParam(name = "search", required = false) String search, Model model) {
//            model.addAttribute("categories", categoryService.getAllCategories());
//
//            List<Product> products;
//            if (search != null && !search.isEmpty()) {
//                // ค้นหาผลิตภัณฑ์ด้วยหมวดหมู่
//                products = productService.searchProductsByCategory(search);
//
//            }else if (search != null && !search.isEmpty()) {
//                // ค้นหาผลิตภัณฑ์ด้วยหมวดหมู่
//                products = productService.searchProductsByCategory(search);
//            } else if (search != null && !search.isEmpty()) {
//                // ค้นหาผลิตภัณฑ์ด้วยชื่อ
//                products = productService.searchProductsByName(search);
//            } else {
//                // ไม่มีค่าหมวดหมู่หรือคำค้นหา
//                products = productService.getAllProducts();
//            }
//
//            model.addAttribute("products", products);
//
//            return "product-all";
//        }
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
//        @GetMapping
//        public String getAllProduct(
//                @RequestParam(name = "category", required = false) String category,
//                @RequestParam(name = "search", required = false) String search,
//                Model model) {
//            List<Product> products;
//
//            if (category != null || search != null) {
//                // ค้นหาด้วยชื่อหรือหมวดหมู่
//                products = productService.searchProductsByNameOrCategory(search, category);
//            } else {
//                // ไม่มีค่าหมวดหมู่หรือคำค้นหา
//                // ดึงรายการสินค้าทั้งหมด
//                products = productService.getAllProducts();
//            }
//
//            model.addAttribute("products", products);
//            return "product-all";
//        }



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

        @PostMapping("/add")
        public String createProduct(@ModelAttribute ProductRequest product,@RequestParam("file")MultipartFile image) {
            productService.createProduct(product,image);
            return "redirect:/main";
        }
    }



