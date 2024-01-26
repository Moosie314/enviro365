package com.enviro.assessment.grade001.moretelemabotja.springboot_application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.enviro.assessment.grade001.moretelemabotja.springboot_application.model.Product;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllById(Long id);
}