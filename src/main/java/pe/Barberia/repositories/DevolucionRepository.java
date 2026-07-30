package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Devolucion;

@Repository
public interface DevolucionRepository extends JpaRepository<Devolucion, Long> {
}