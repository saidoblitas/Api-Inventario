package com.evaluacion.inventario.Services;

import com.evaluacion.inventario.Models.Product;
import com.evaluacion.inventario.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productoRepository;

    public Product registrarProducto(Product producto) {
        return productoRepository.save(producto);
    }

    public Product modificarProducto(Long id, Product producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public Optional<Product> buscarProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public List<Product> buscarTodos() {
        return productoRepository.findAll();
    }

    public Page<Product> buscarProductosPaginados(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }
}
