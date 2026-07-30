package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
}
