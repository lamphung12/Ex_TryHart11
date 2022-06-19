package com.example.tryhart11.service.product;

import com.example.tryhart11.model.Category;
import com.example.tryhart11.model.Product;
import com.example.tryhart11.service.IGeneralService;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IProductService extends IGeneralService<Product> {
    Optional<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByNameContaining(String name);

    Iterable<Product> findTop4New();

    Iterable<Product> findAllByPriceAsc();

    Iterable<Product> findAllByPrice();

    Iterable<Product> findAllByPriceBetween (int one ,int two);

    Iterable<Product> findAllByCategory_Id(Long id);

}
