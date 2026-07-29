package pe.Barberia.service;

import org.springframework.stereotype.Service;
import pe.Barberia.entities.Producto;
import pe.Barberia.enums.CategoriaProducto;
import pe.Barberia.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Producto> listarPorCategoria(CategoriaProducto categoria) {
        return productoRepository.findByCategoria(categoria);
    }

    public List<Producto> listarStockBajo() {
        return productoRepository.findByStockLessThanEqual(5);
    }

    public Producto registrar(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizar(Long id, Producto productoActualizado) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        producto.setNombre(productoActualizado.getNombre());
        producto.setMarca(productoActualizado.getMarca());
        producto.setDescripcionCorta(productoActualizado.getDescripcionCorta());
        producto.setDescripcionLarga(productoActualizado.getDescripcionLarga());
        producto.setImagenUrl(productoActualizado.getImagenUrl());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());
        producto.setStockMinimo(productoActualizado.getStockMinimo());
        producto.setCategoria(productoActualizado.getCategoria());
        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}
