package pe.Barberia.service;

import org.springframework.stereotype.Service;
import pe.Barberia.entities.Resenia;
import pe.Barberia.repositories.ReseniaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReseniaService {

    private final ReseniaRepository reseniaRepository;

    public ReseniaService(ReseniaRepository reseniaRepository) {
        this.reseniaRepository = reseniaRepository;
    }

    public List<Resenia> listarTodos() {
        return reseniaRepository.findAll();
    }

    public Optional<Resenia> buscarPorId(Long id) {
        return reseniaRepository.findById(id);
    }

    public List<Resenia> listarPorProducto(Long productoId) {
        return reseniaRepository.findByProductoId(productoId);
    }

    public List<Resenia> listarPorUsuario(Long usuarioId) {
        return reseniaRepository.findByUsuarioId(usuarioId);
    }

    public Resenia registrar(Resenia resenia) {
        resenia.setFechaCreacion(LocalDateTime.now());
        return reseniaRepository.save(resenia);
    }

    public Resenia actualizar(Long id, Resenia reseniaActualizada) {
        Resenia resenia = reseniaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reseña no encontrada con id: " + id));
        resenia.setProducto(reseniaActualizada.getProducto());
        resenia.setUsuario(reseniaActualizada.getUsuario());
        resenia.setEstrellas(reseniaActualizada.getEstrellas());
        resenia.setComentario(reseniaActualizada.getComentario());

        return reseniaRepository.save(resenia);
    }

    public void eliminar(Long id) {
        if (!reseniaRepository.existsById(id)) {
            throw new RuntimeException("Reseña no encontrada con id: " + id);
        }
        reseniaRepository.deleteById(id);
    }
}