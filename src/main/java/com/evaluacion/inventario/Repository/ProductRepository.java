package com.evaluacion.inventario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluacion.inventario.Models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
