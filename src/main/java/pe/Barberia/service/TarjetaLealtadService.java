package pe.Barberia.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import pe.Barberia.entities.TarjetaLealtad;
import pe.Barberia.repositories.TarjetaLealtadRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaLealtadService {

    private final TarjetaLealtadRepository tarjetaLealtadRepository;

    @PersistenceContext
    private EntityManager em;

    public TarjetaLealtadService(TarjetaLealtadRepository tarjetaLealtadRepository) {
        this.tarjetaLealtadRepository = tarjetaLealtadRepository;
    }

    public List<TarjetaLealtad> listarTodos() {
        return tarjetaLealtadRepository.findAll();
    }

    public Optional<TarjetaLealtad> buscarPorId(Long id) {
        return tarjetaLealtadRepository.findById(id);
    }

    public Optional<TarjetaLealtad> buscarPorCelular(String celularCliente) {
        List<TarjetaLealtad> resultados = em.createQuery(
                "SELECT t FROM TarjetaLealtad t WHERE t.celularCliente = :cel", TarjetaLealtad.class)
                .setParameter("cel", celularCliente)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
        return resultados.isEmpty() ? Optional.empty() : Optional.of(resultados.get(0));
    }

    public TarjetaLealtad registrar(TarjetaLealtad tarjeta) {
        if (tarjeta.getCantidadSellos() == null) {
            tarjeta.setCantidadSellos(0);
        }
        return tarjetaLealtadRepository.save(tarjeta);
    }

    public TarjetaLealtad actualizar(Long id, TarjetaLealtad tarjetaActualizada) {
        TarjetaLealtad tarjeta = tarjetaLealtadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarjeta de lealtad no encontrada con id: " + id));
        tarjeta.setCelularCliente(tarjetaActualizada.getCelularCliente());
        tarjeta.setCantidadSellos(tarjetaActualizada.getCantidadSellos());

        return tarjetaLealtadRepository.save(tarjeta);
    }

    public void eliminar(Long id) {
        if (!tarjetaLealtadRepository.existsById(id)) {
            throw new RuntimeException("Tarjeta de lealtad no encontrada con id: " + id);
        }
        tarjetaLealtadRepository.deleteById(id);
    }
}