package pe.Barberia.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import pe.Barberia.entities.Usuario;
import pe.Barberia.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @PersistenceContext
    private EntityManager em;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario registrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizar(Long id, Usuario usuarioActualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        usuario.setNombreUsuario(usuarioActualizado.getNombreUsuario());
        usuario.setContrasenia(usuarioActualizado.getContrasenia());
        usuario.setEmail(usuarioActualizado.getEmail());
        usuario.setTelefono(usuarioActualizado.getTelefono());
        usuario.setRol(usuarioActualizado.getRol());
        usuario.setTipoRegistro(usuarioActualizado.getTipoRegistro());
        usuario.setIdGoogle(usuarioActualizado.getIdGoogle());
        usuario.setRecibirPromociones(usuarioActualizado.isRecibirPromociones());
        usuario.setActivo(usuarioActualizado.isActivo());
        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> login(String email, String contrasenia) {
        List<Usuario> resultados = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                .setParameter("email", email)
                .setHint("org.hibernate.fetchSize", 5)
                .getResultList();
        return resultados.isEmpty() ? Optional.empty() :
                resultados.stream().filter(u -> u.getContrasenia().equals(contrasenia)).findFirst();
    }
}
