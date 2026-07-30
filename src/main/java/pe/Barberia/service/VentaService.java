package pe.Barberia.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import pe.Barberia.entities.Cita;
import pe.Barberia.entities.DireccionEnvio;
import pe.Barberia.entities.Usuario;
import pe.Barberia.entities.Venta;
import pe.Barberia.enums.EstadoPedido;
import pe.Barberia.repositories.VentaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    @PersistenceContext
    private EntityManager em;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> listarTodos() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public List<Venta> listarPorComprador(Long compradorId) {
        return em.createQuery("SELECT v FROM Venta v WHERE v.comprador.id = :id", Venta.class)
                .setParameter("id", compradorId)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public List<Venta> listarPorCita(Long citaId) {
        return em.createQuery("SELECT v FROM Venta v WHERE v.citaAsociada.id = :id", Venta.class)
                .setParameter("id", citaId)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public List<Venta> listarPorEstado(EstadoPedido estadoPedido) {
        return em.createQuery("SELECT v FROM Venta v WHERE v.estadoPedido = :est", Venta.class)
                .setParameter("est", estadoPedido)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public Venta registrar(Venta venta) {
        if (venta.getComprador() != null) {
            venta.setComprador(em.getReference(Usuario.class, venta.getComprador().getId()));
        }
        if (venta.getCitaAsociada() != null) {
            venta.setCitaAsociada(em.getReference(Cita.class, venta.getCitaAsociada().getId()));
        }
        if (venta.getDireccionEnvio() != null) {
            venta.setDireccionEnvio(em.getReference(DireccionEnvio.class, venta.getDireccionEnvio().getId()));
        }
        venta.setFechaTransaccion(LocalDateTime.now());
        return ventaRepository.save(venta);
    }

    public Venta actualizar(Long id, Venta ventaActualizada) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));

        venta.setComprador(ventaActualizada.getComprador());
        venta.setCitaAsociada(ventaActualizada.getCitaAsociada());
        venta.setMontoTotal(ventaActualizada.getMontoTotal());
        venta.setMedioPago(ventaActualizada.getMedioPago());
        venta.setTipoDespacho(ventaActualizada.getTipoDespacho());
        venta.setOrigenOrden(ventaActualizada.getOrigenOrden());
        venta.setDireccionEnvio(ventaActualizada.getDireccionEnvio());
        venta.setCoordenadasEnvio(ventaActualizada.getCoordenadasEnvio());
        venta.setReferenciaDireccion(ventaActualizada.getReferenciaDireccion());
        venta.setCostoEnvio(ventaActualizada.getCostoEnvio());
        venta.setEstadoPedido(ventaActualizada.getEstadoPedido());

        return ventaRepository.save(venta);
    }

    public void eliminar(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con id: " + id);
        }
        ventaRepository.deleteById(id);
    }
}