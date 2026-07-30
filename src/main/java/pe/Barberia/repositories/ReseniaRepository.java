package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Resenia;

@Repository
public interface ReseniaRepository extends JpaRepository<Resenia, Long> {
}