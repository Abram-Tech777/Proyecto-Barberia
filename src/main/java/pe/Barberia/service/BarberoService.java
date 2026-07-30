package pe.Barberia.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import pe.Barberia.entities.Barbero;
import pe.Barberia.entities.Usuario;
import pe.Barberia.repositories.BarberoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BarberoService {

    private final BarberoRepository barberoRepository;

    @PersistenceContext
    private EntityManager em;

    public BarberoService(BarberoRepository barberoRepository) {
        this.barberoRepository = barberoRepository;
    }

    public List<Barbero> listarTodos() {
        return barberoRepository.findAll();
    }

    public List<Barbero> listarActivos() {
        return em.createQuery("SELECT b FROM Barbero b WHERE b.activo = true", Barbero.class)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
    }

    public Optional<Barbero> buscarPorId(Long id) {
        return barberoRepository.findById(id);
    }

    public Barbero registrar(Barbero barbero) {
        if (barbero.getUsuario() != null) {
            barbero.setUsuario(em.getReference(Usuario.class, barbero.getUsuario().getId()));
        }
        return barberoRepository.save(barbero);
    }

    public Barbero actualizar(Long id, Barbero barberoActualizado) {
        Barbero barbero = barberoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbero no encontrado con id: " + id));
        barbero.setNombreCompleto(barberoActualizado.getNombreCompleto());
        barbero.setPorcentajeComision(barberoActualizado.getPorcentajeComision());
        barbero.setUsuario(barberoActualizado.getUsuario());
        barbero.setActivo(barberoActualizado.isActivo());
        return barberoRepository.save(barbero);
    }

    public void eliminar(Long id) {
        if (!barberoRepository.existsById(id)) {
            throw new RuntimeException("Barbero no encontrado con id: " + id);
        }
        barberoRepository.deleteById(id);
    }
}
