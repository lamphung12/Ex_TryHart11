package com.example.tryhart11.service;

import com.example.tryhart11.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IGeneralService<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remote(Long id);

//    Page<T> findAllByNameContaining(Pageable pageable, String name);

}
