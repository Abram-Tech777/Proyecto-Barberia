package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Venta;
import pe.Barberia.enums.EstadoPedido;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByCompradorId(Long compradorId);
    List<Venta> findByCitaAsociadaId(Long citaId);
    List<Venta> findByEstadoPedido(EstadoPedido estadoPedido);
}