package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.TarjetaLealtad;

@Repository
public interface TarjetaLealtadRepository extends JpaRepository<TarjetaLealtad, Long> {
}