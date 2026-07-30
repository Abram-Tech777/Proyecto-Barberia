package pe.Barberia.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import pe.Barberia.entities.Promocion;
import pe.Barberia.repositories.PromocionRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromocionService {

    private final PromocionRepository promocionRepository;

    @PersistenceContext
    private EntityManager em;

    public PromocionService(PromocionRepository promocionRepository) {
        this.promocionRepository = promocionRepository;
    }

    public List<Promocion> listarTodos() {
        return promocionRepository.findAll();
    }

    public Optional<Promocion> buscarPorId(Long id) {
        return promocionRepository.findById(id);
    }

    public List<Promocion> listarActivos() {
        return em.createQuery("SELECT p FROM Promocion p WHERE p.activo = true", Promocion.class)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public List<Promocion> listarVigentes(LocalDate fecha) {
        return em.createQuery(
                "SELECT p FROM Promocion p WHERE p.fechaInicio <= :fecha AND p.fechaFin >= :fecha", Promocion.class)
                .setParameter("fecha", fecha)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public Promocion registrar(Promocion promocion) {
        return promocionRepository.save(promocion);
    }

    public Promocion actualizar(Long id, Promocion promocionActualizada) {
        Promocion promocion = promocionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promoción no encontrada con id: " + id));
        promocion.setNombre(promocionActualizada.getNombre());
        promocion.setPorcentajeDescuento(promocionActualizada.getPorcentajeDescuento());
        promocion.setFechaInicio(promocionActualizada.getFechaInicio());
        promocion.setFechaFin(promocionActualizada.getFechaFin());
        promocion.setActivo(promocionActualizada.isActivo());
        promocion.setServicios(promocionActualizada.getServicios());
        promocion.setProductos(promocionActualizada.getProductos());

        return promocionRepository.save(promocion);
    }

    public void eliminar(Long id) {
        if (!promocionRepository.existsById(id)) {
            throw new RuntimeException("Promoción no encontrada con id: " + id);
        }
        promocionRepository.deleteById(id);
    }
}