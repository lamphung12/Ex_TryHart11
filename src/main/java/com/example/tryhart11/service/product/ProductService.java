package com.example.tryhart11.service.product;

import com.example.tryhart11.model.Category;
import com.example.tryhart11.model.Product;
import com.example.tryhart11.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
   private ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
      return   productRepository.save(product);
    }

    @Override
    public void remote(Long id) {
        productRepository.deleteById(id);
    }

//    @Override
//    public Page<Product> findAllByNameContaining(Pageable pageable, String name) {
//
//        return null;
//    }

    @Override
    public Optional<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Product> findAllByNameContaining(String name) {
      return productRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Product> findTop4New() {
        return productRepository.findTop4New();
    }

    @Override
    public Iterable<Product> findAllByPriceAsc() {
        return productRepository.findAllByPriceAsc();
    }

    @Override
    public Iterable<Product> findAllByPrice() {
        return productRepository.findAllByPrice();
    }

    @Override
    public Iterable<Product> findAllByPriceBetween(int one, int two) {
        return productRepository.findAllByPriceBetween(one, two);
    }

    @Override
    public Iterable<Product> findAllByCategory_Name(String name) {
        return productRepository.findAllByCategory_Name(name);
    }

    @Override
    public Iterable<Product> findAllByOrderByPrice() {
        return productRepository.findAllByOrderByPrice();
    }

    @Override
    public Page<Product> findAllByCategory_Id(Long id, Pageable pageable) {
        return productRepository.findAllByCategory_Id(id,pageable);
    }


}

