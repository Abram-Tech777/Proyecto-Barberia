package pe.Barberia.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import pe.Barberia.entities.Barbero;
import pe.Barberia.entities.Cita;
import pe.Barberia.enums.EstadoCita;
import pe.Barberia.repositories.CitaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {

    private final CitaRepository citaRepository;

    @PersistenceContext
    private EntityManager em;

    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    public List<Cita> listarTodos() {
        return citaRepository.findAll();
    }

    public Optional<Cita> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    public List<Cita> listarPorBarbero(Long barberoId) {
        return em.createQuery("SELECT c FROM Cita c WHERE c.barbero.id = :id", Cita.class)
                .setParameter("id", barberoId)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public List<Cita> listarPorCelular(String celular) {
        return em.createQuery("SELECT c FROM Cita c WHERE c.celularCliente = :cel", Cita.class)
                .setParameter("cel", celular)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public List<Cita> listarPendientesValidar() {
        return em.createQuery("SELECT c FROM Cita c WHERE c.estado = :est", Cita.class)
                .setParameter("est", EstadoCita.PENDIENTE_VALIDAR)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public Cita registrar(Cita cita) {
        if (cita.getBarbero() != null) {
            cita.setBarbero(em.getReference(Barbero.class, cita.getBarbero().getId()));
        }
        cita.setFechaCreacion(LocalDateTime.now());
        cita.setEstado(EstadoCita.PENDIENTE_VALIDAR);
        return citaRepository.save(cita);
    }

    public Cita actualizar(Long id, Cita citaActualizada) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
        cita.setBarbero(citaActualizada.getBarbero());
        cita.setNombreCliente(citaActualizada.getNombreCliente());
        cita.setCelularCliente(citaActualizada.getCelularCliente());
        cita.setHoraInicio(citaActualizada.getHoraInicio());
        cita.setHoraFin(citaActualizada.getHoraFin());
        cita.setEstado(citaActualizada.getEstado());
        cita.setCodigoPago(citaActualizada.getCodigoPago());
        cita.setTipoPago(citaActualizada.getTipoPago());
        cita.setMontoTotal(citaActualizada.getMontoTotal());
        cita.setMontoAbonado(citaActualizada.getMontoAbonado());
        cita.setSaldoPendiente(citaActualizada.getSaldoPendiente());
        cita.setSelloAplicado(citaActualizada.isSelloAplicado());
        return citaRepository.save(cita);
    }

    public void eliminar(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new RuntimeException("Cita no encontrada con id: " + id);
        }
        citaRepository.deleteById(id);
    }

    public int cancelarSpam() {
        LocalDateTime hace15Minutos = LocalDateTime.now().minusMinutes(15);
        List<Cita> citasSpam = em.createQuery(
                "SELECT c FROM Cita c WHERE c.estado = :est AND c.fechaCreacion < :limite", Cita.class)
                .setParameter("est", EstadoCita.PENDIENTE_VALIDAR)
                .setParameter("limite", hace15Minutos)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
        for (Cita cita : citasSpam) {
            cita.setEstado(EstadoCita.CANCELADA);
        }
        citaRepository.saveAll(citasSpam);
        return citasSpam.size();
    }
}
