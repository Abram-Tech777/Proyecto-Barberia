package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Barbero;

import java.util.List;

@Repository
public interface BarberoRepository extends JpaRepository<Barbero, Long> {

    //para traer barberos activos que estan trabajando
    List<Barbero> findByActivoTrue();
}
