package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Resenia;

import java.util.List;

@Repository
public interface ReseniaRepository extends JpaRepository<Resenia, Long> {
    List<Resenia> findByProductoId(Long productoId);
    List<Resenia> findByUsuarioId(Long usuarioId);
}