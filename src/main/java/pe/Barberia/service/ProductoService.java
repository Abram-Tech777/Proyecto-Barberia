package pe.Barberia.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import pe.Barberia.entities.Producto;
import pe.Barberia.enums.CategoriaProducto;
import pe.Barberia.repositories.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @PersistenceContext
    private EntityManager em;

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
        return em.createQuery(
                "SELECT p FROM Producto p WHERE UPPER(p.nombre) LIKE UPPER(CONCAT('%', :nom, '%'))", Producto.class)
                .setParameter("nom", nombre)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public List<Producto> listarPorCategoria(CategoriaProducto categoria) {
        return em.createQuery("SELECT p FROM Producto p WHERE p.categoria = :cat", Producto.class)
                .setParameter("cat", categoria)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public List<Producto> listarStockBajo() {
        return em.createQuery(
                "SELECT p FROM Producto p WHERE p.stock <= p.stockMinimo", Producto.class)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
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
