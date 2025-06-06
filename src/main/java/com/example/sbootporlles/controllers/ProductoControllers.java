package com.example.sbootporlles.controllers;

import com.example.sbootporlles.models.ProductoModel;
import com.example.sbootporlles.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoControllers {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<ProductoModel> listarProductos() {
        return productoService.obtenerTodosProducto();
    }

    @GetMapping("/{id}")
    public Optional<ProductoModel> obtenerProductoPorId(@PathVariable int id) {
        return productoService.obtenerPorId(id);
    }

    @GetMapping("/crear")
    public ProductoModel crearProducto(@RequestBody ProductoModel producto) {
         System.out.println(producto.getNombre()+"::");
        return productoService.guardarProducto(producto);
    }

    @PutMapping("/{id}")
    public ProductoModel actualizarProducto( @RequestBody ProductoModel producto,@PathVariable("id") int id) {
        return productoService.actualizarProducto(producto,id);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable int id) {
        productoService.eliminarProducto(id);
    }
}
