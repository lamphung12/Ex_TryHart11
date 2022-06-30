package com.example.tryhart11.repository;

import com.example.tryhart11.model.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository  extends JpaRepository<House , Long> {
}
