package com.example.tryhart11.service.category;

import com.example.tryhart11.model.Category;
import com.example.tryhart11.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void remote(Long id) {
        categoryRepository.deleteById(id);

    }

//    @Override
//    public Page<Category> findAllByNameContaining(Pageable pageable, String name) {
//
//        return null;
//    }
}
