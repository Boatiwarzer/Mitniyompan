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
import sa.system.Midniyompan.model.AddProductRequest;
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
    public boolean isInventoryEnough(UUID id,int quantity){
        Product product = productRepository.findById(id).get();
        return product.getInventory() - quantity   >= 0;
    }

    public Product getOneById(UUID id) {
        return productRepository.findById(id).get();
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
    public void addProductPlusDelete(AddProductRequest request,UUID id){
        Product record = modelMapper.map(request,Product.class);
        Category category =
                categoryRepository.findById(request.getCategoryId()).get();
        record.setCategory(category);
        if (request.getRemain() > 0){
            record.setIncreaseInventory(request.getRemain());
            record.setInventory(record.getInventory() + request.getRemain());
        }else {
            record.setDecreaseInventory(request.getRemain());
            record.setInventory(record.getInventory() - request.getRemain());
        }
        record.setId(id);
        productRepository.save(record);

    }


    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }


    public void editProduct(ProductRequest request, MultipartFile file,UUID id) {
        Product record = modelMapper.map(request, Product.class);
        Category category =
                categoryRepository.findById(request.getCategoryId()).get();
        record.setCategory(category);
        record.setId(id);
        try {
            record.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        productRepository.save(record);
    }
}

