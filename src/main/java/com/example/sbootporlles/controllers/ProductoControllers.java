package com.example.sbootporlles.controllers;

import com.example.sbootporlles.models.ProductoModel;
import com.example.sbootporlles.services.ProductoService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@RestController
@RequestMapping("/productos")
public class ProductoControllers {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/GetAll")
    public List<ProductoModel> listarProductos() {
        return productoService.obtenerTodosProducto();
    }

    @PostMapping ("/{id}")
    public Optional<ProductoModel> obtenerProductoPorId(@PathVariable int id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping("/crear")
    public ProductoModel crearProducto(
        @RequestPart("producto") ProductoModel producto,
        @RequestPart("imagen") MultipartFile imagen) throws IOException {
        producto.setImagen(imagen.getBytes());
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
    @GetMapping("/imagen/{id}")
    public ResponseEntity<byte[]> obtenerImagenProducto(@PathVariable int id) {
        Optional<ProductoModel> productoOpt = productoService.obtenerPorId(id);
        if (productoOpt.isPresent() && productoOpt.get().getImagen() != null) {
            byte[] imagen = productoOpt.get().getImagen();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // O IMAGE_PNG según corresponda
            return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
