package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Promocion;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Long> {
}