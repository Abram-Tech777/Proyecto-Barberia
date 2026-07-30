package pe.Barberia.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import pe.Barberia.entities.Servicio;
import pe.Barberia.repositories.ServicioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioService {

    private final ServicioRepository servicioRepository;

    @PersistenceContext
    private EntityManager em;

    public ServicioService(ServicioRepository servicioRepository) {
        this.servicioRepository = servicioRepository;
    }

    public List<Servicio> listarTodos() {
        return servicioRepository.findAll();
    }

    public Optional<Servicio> buscarPorId(Long id) {
        return servicioRepository.findById(id);
    }

    public List<Servicio> buscarPorNombre(String nombre) {
        return em.createQuery(
                "SELECT s FROM Servicio s WHERE UPPER(s.nombre) LIKE UPPER(CONCAT('%', :nom, '%'))", Servicio.class)
                .setParameter("nom", nombre)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public Servicio registrar(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Servicio actualizar(Long id, Servicio servicioActualizado) {
        Servicio servicio = servicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado con id: " + id));
        servicio.setNombre(servicioActualizado.getNombre());
        servicio.setDescripcion(servicioActualizado.getDescripcion());
        servicio.setDuracionMinutos(servicioActualizado.getDuracionMinutos());
        servicio.setPrecioBase(servicioActualizado.getPrecioBase());
        return servicioRepository.save(servicio);
    }

    public void eliminar(Long id) {
        if (!servicioRepository.existsById(id)) {
            throw new RuntimeException("Servicio no encontrado con id: " + id);
        }
        servicioRepository.deleteById(id);
    }
}
