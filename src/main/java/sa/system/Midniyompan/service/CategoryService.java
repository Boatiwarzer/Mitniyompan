package sa.system.Midniyompan.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sa.system.Midniyompan.entity.Category;
import sa.system.Midniyompan.model.CategoryRequest;
import sa.system.Midniyompan.repository.CategoryRepository;


import java.util.List;


@Service
public class CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;


    @Autowired
    private ModelMapper modelMapper;


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }


    public void createCategory(CategoryRequest request) {
        Category record = modelMapper.map(request, Category.class);
        categoryRepository.save(record);
    }
}

