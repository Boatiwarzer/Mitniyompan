package sa.system.Midniyompan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sa.system.Midniyompan.entity.Category;
import sa.system.Midniyompan.entity.Product;
import sa.system.Midniyompan.model.ProductRequest;
import sa.system.Midniyompan.repository.CategoryRepository;
import sa.system.Midniyompan.repository.ProductRepository;


import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;


@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    public Product getOneById(UUID id) {
        return productRepository.findById(id).get();
    }


    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void createProduct(ProductRequest request,MultipartFile file) {
        Product record = modelMapper.map(request, Product.class);
        Category category =
                categoryRepository.findById(request.getCategoryId()).get();
        record.setCategory(category);
        try {
            record.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        productRepository.save(record);
    }


//    public List<Product> searchProductsByCategory(String categoryName) {
//        Category category = categoryRepository.findByName(categoryName);
//        return productRepository.findByCategory(category);
//    }

//    public List<Product> searchProductsByName(String name) {
//        return productRepository.findByName(name);
//
//    }
    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }
}

