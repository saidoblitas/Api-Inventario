package com.evaluacion.inventario.Services;

import com.evaluacion.inventario.Models.Producto;
import com.evaluacion.inventario.Repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public Producto registrarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto modificarProducto(Long id, Producto producto) {
        producto.setId(id);
        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    public Optional<Producto> buscarProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public List<Producto> buscarTodos() {
        return productoRepository.findAll();
    }

    public Page<Producto> buscarProductosPaginados(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }
}
