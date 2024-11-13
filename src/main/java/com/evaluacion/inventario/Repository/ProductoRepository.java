package com.evaluacion.inventario.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evaluacion.inventario.Models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
