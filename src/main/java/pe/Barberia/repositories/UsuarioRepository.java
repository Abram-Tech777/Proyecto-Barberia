package pe.Barberia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.Barberia.entities.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);

    Optional<Usuario> findByTelefono(String telefono);

    Optional<Usuario> findByProveedorId(String proveedorId);
}
