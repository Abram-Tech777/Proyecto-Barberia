package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.DireccionEnvio;

@Repository
public interface DireccionEnvioRepository extends JpaRepository<DireccionEnvio, Long> {
}
