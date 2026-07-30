package pe.Barberia.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import pe.Barberia.entities.Cita;
import pe.Barberia.entities.Devolucion;
import pe.Barberia.repositories.DevolucionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DevolucionService {

    private final DevolucionRepository devolucionRepository;

    @PersistenceContext
    private EntityManager em;

    public DevolucionService(DevolucionRepository devolucionRepository) {
        this.devolucionRepository = devolucionRepository;
    }

    public List<Devolucion> listarTodos() {
        return devolucionRepository.findAll();
    }

    public Optional<Devolucion> buscarPorId(Long id) {
        return devolucionRepository.findById(id);
    }

    public Optional<Devolucion> buscarPorCita(Long citaId) {
        List<Devolucion> resultados = em.createQuery(
                "SELECT d FROM Devolucion d WHERE d.cita.id = :id", Devolucion.class)
                .setParameter("id", citaId)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
        return resultados.isEmpty() ? Optional.empty() : Optional.of(resultados.get(0));
    }

    public List<Devolucion> listarPorCelular(String celularCliente) {
        return em.createQuery(
                "SELECT d FROM Devolucion d WHERE d.celularCliente = :cel", Devolucion.class)
                .setParameter("cel", celularCliente)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public Devolucion registrar(Devolucion devolucion) {
        devolucion.setCita(em.getReference(Cita.class, devolucion.getCita().getId()));
        devolucion.setFechaRegistro(LocalDateTime.now());
        return devolucionRepository.save(devolucion);
    }

    public Devolucion actualizar(Long id, Devolucion devolucionActualizada) {
        Devolucion devolucion = devolucionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Devolución no encontrada con id: " + id));

        devolucion.setCita(devolucionActualizada.getCita());
        devolucion.setMontoReembolso(devolucionActualizada.getMontoReembolso());
        devolucion.setCelularCliente(devolucionActualizada.getCelularCliente());
        devolucion.setEstado(devolucionActualizada.getEstado());

        return devolucionRepository.save(devolucion);
    }

    public void eliminar(Long id) {
        if (!devolucionRepository.existsById(id)) {
            throw new RuntimeException("Devolución no encontrada con id: " + id);
        }
        devolucionRepository.deleteById(id);
    }
}