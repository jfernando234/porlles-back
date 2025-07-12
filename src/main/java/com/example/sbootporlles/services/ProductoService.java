package com.example.sbootporlles.services;

import com.example.sbootporlles.models.ProductoModel;
import com.example.sbootporlles.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<ProductoModel> obtenerTodosProducto() {
        return productoRepository.findAll();
    }

    public Optional<ProductoModel> obtenerPorId(int id) {
        return productoRepository.findById(id);
    }

    public ProductoModel guardarProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }   
    public ProductoModel actualizarProducto( ProductoModel productoActualizado,int id) {
        return productoRepository.findById(id).map(producto -> {
            producto.setNombre(productoActualizado.getNombre());
            producto.setMarca(productoActualizado.getMarca());
            producto.setEspecificaciones(productoActualizado.getEspecificaciones());
            producto.setPrecioUnitario(productoActualizado.getPrecioUnitario());
            producto.setProveedor(productoActualizado.getProveedor());
            producto.setStockMinimo(productoActualizado.getStockMinimo());
            return productoRepository.save(producto);
        }).orElse(null);
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }
    public byte[] obtenerImagenPorId(int  id) {
        ProductoModel producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        
        if (producto.getImagen() == null) {
            throw new RuntimeException("Producto no tiene imagen");
        }

        return producto.getImagen();
    }
}
