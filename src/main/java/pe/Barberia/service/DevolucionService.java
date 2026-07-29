package pe.Barberia.service;

import org.springframework.stereotype.Service;
import pe.Barberia.entities.Devolucion;
import pe.Barberia.repositories.DevolucionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DevolucionService {

    private final DevolucionRepository devolucionRepository;

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
        return devolucionRepository.findByCitaId(citaId);
    }

    public List<Devolucion> listarPorCelular(String celularCliente) {
        return devolucionRepository.findByCelularCliente(celularCliente);
    }

    public Devolucion registrar(Devolucion devolucion) {
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