package pe.Barberia.service;

import org.springframework.stereotype.Service;
import pe.Barberia.entities.Usuario;
import pe.Barberia.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

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
        return usuarioRepository.findByEmail(email)
                .filter(u -> u.getContrasenia().equals(contrasenia));
    }
}
