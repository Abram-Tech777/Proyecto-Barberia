package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Cita;
import pe.Barberia.enums.EstadoCita;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByBarberoId(Long barberoId);

    List<Cita> findByEstado(EstadoCita estado);

    List<Cita> findByCelularCliente(String celularCliente);

    //busca las las citas que estan en pendiente con una fecha de creacion antes del limite
    //para borrar citas que quedan en nada o son spam
    List<Cita> findByEstadoAndFechaCreacionBefore(EstadoCita estado, LocalDateTime fechaLimite);
}
