package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
}