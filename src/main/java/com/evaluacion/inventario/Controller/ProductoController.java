package com.evaluacion.inventario.Controller;

import com.evaluacion.inventario.Models.Product;
import com.evaluacion.inventario.Services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v0/products")
public class ProductoController {

    @Autowired
    private ProductService productService;

    @Operation(summary = "Registrar un nuevo producto")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Producto creado",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)) }),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta",
                    content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<Product> registrarProducto(@RequestBody Product producto) {
        try {
            Product productoRegistrado = productService.registrarProducto(producto);
            return new ResponseEntity<>(productoRegistrado, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Modificar un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto modificado",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)) }),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta",
                    content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Product> modificarProducto(@PathVariable Long id, @RequestBody Product producto) {
        try {
            Product productoModificado = productService.modificarProducto(id, producto);
            return new ResponseEntity<>(productoModificado, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Eliminar un producto existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Producto eliminado",
                    content = @Content),
        @ApiResponse(responseCode = "400", description = "Solicitud incorrecta",
                    content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        try {
            productService.eliminarProducto(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar un producto por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product.class)) }),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<Product> buscarProductoPorId(@PathVariable Long id) {
        try {
            Optional<Product> producto = productService.buscarProductoPorId(id);
            return producto.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                           .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "Buscar todos los productos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos encontrados",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product[].class)) }),
        @ApiResponse(responseCode = "204", description = "No hay productos",
                    content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<Product>> buscarTodos() {
        List<Product> productos = productService.buscarTodos();
        if (productos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    @Operation(summary = "Buscar productos paginados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Productos encontrados",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Product[].class)) }),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content)
    })
    @GetMapping("/paginado")
    public ResponseEntity<Page<Product>> buscarProductosPaginados(@RequestParam int page, @RequestParam int size) {
        try {
            Page<Product> productosPaginados = productService.buscarProductosPaginados(PageRequest.of(page, size));
            return new ResponseEntity<>(productosPaginados, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}