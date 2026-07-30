package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Barbero;

@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Long> {
}
