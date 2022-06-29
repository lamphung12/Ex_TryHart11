package com.example.tryhart11.repository;

import com.example.tryhart11.model.Category;
import com.example.tryhart11.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

//    Page<Product> findAllByNameContaining(Pageable pageable, String name);

    Iterable<Product> findAllByCategory_Name(String name);



    Optional<Product> findAllByCategory(Category category);

    Iterable<Product> findAllByNameContaining(String name);

    Iterable<Product> findAllByPriceBetween (int one ,int two);

    @Query(value = "select * from product order by price desc limit 4 ", nativeQuery = true)
    Iterable<Product> findTop4New();

    @Query(value = "select * from product order by price asc ", nativeQuery = true)
    Iterable<Product> findAllByPriceAsc();

    @Query(value = "select * from product order by price  ", nativeQuery = true)
    Iterable<Product> findAllByPrice();

    Iterable<Product> findAllByOrderByPrice();

    Page<Product> findAllByCategory_Id(Long id,Pageable pageable);




}
