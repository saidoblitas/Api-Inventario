package com.evaluacion.inventario.Controller;

import com.evaluacion.inventario.Models.Producto;
import com.evaluacion.inventario.Services.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> registrarProducto(@RequestBody Producto producto) {
        return new ResponseEntity<>(productoService.registrarProducto(producto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> modificarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return new ResponseEntity<>(productoService.modificarProducto(id, producto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.buscarProductoPorId(id);
        return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                       .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> buscarTodos() {
        return new ResponseEntity<>(productoService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<Producto>> buscarProductosPaginados(@RequestParam int page, @RequestParam int size) {
        return new ResponseEntity<>(productoService.buscarProductosPaginados(PageRequest.of(page, size)), HttpStatus.OK);
    }
}
