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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @GetMapping("/{id}/imagen")
        public ResponseEntity<byte[]> obtenerImagen(@PathVariable int id) {
            byte[] imagen = productoService.obtenerImagenPorId(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // o IMAGE_PNG según el tipo real
            return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public Optional<ProductoModel> obtenerProductoPorId(@PathVariable int id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping("/crear")
    public Map<String, Object> crearProducto(
        @RequestPart("producto") ProductoModel producto,
        @RequestPart("imagen") MultipartFile imagen) throws IOException {
        producto.setImagen(imagen.getBytes());
        ProductoModel guardado = productoService.guardarProducto(producto);
        Map<String, Object> response = new HashMap<>();
        response.put("isSuccess", true);
        response.put("producto", guardado);
        return response;
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
