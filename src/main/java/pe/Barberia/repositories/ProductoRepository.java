package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Producto;
import pe.Barberia.enums.CategoriaProducto;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // esto es para buscar productos con un nombre especifico ignorando mayusculas y minusculas
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByCategoria(CategoriaProducto categoria);

    // busca productos con un stock menor o igual  al stockminimo
    List<Producto> findByStockLessThanEqual(Integer stockMinimo);
}
