package pe.Barberia.service;

import org.springframework.stereotype.Service;
import pe.Barberia.entities.DireccionEnvio;
import pe.Barberia.repositories.DireccionEnvioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionEnvioService {

    private final DireccionEnvioRepository direccionEnvioRepository;

    public DireccionEnvioService(DireccionEnvioRepository direccionEnvioRepository) {
        this.direccionEnvioRepository = direccionEnvioRepository;
    }

    public List<DireccionEnvio> listarTodos() {
        return direccionEnvioRepository.findAll();
    }

    public Optional<DireccionEnvio> buscarPorId(Long id) {
        return direccionEnvioRepository.findById(id);
    }

    public List<DireccionEnvio> listarPorUsuario(Long usuarioId) {
        return direccionEnvioRepository.findByUsuarioId(usuarioId);
    }

    public DireccionEnvio registrar(DireccionEnvio direccionEnvio) {
        return direccionEnvioRepository.save(direccionEnvio);
    }

    public DireccionEnvio actualizar(Long id, DireccionEnvio direccionActualizada) {
        DireccionEnvio direccion = direccionEnvioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con id: " + id));
        direccion.setNombreDireccion(direccionActualizada.getNombreDireccion());
        direccion.setDireccion(direccionActualizada.getDireccion());
        direccion.setDistrito(direccionActualizada.getDistrito());
        direccion.setProvincia(direccionActualizada.getProvincia());
        direccion.setDepartamento(direccionActualizada.getDepartamento());
        direccion.setCodigoPostal(direccionActualizada.getCodigoPostal());
        direccion.setTelefonoContacto(direccionActualizada.getTelefonoContacto());
        return direccionEnvioRepository.save(direccion);
    }

    public void eliminar(Long id) {
        if (!direccionEnvioRepository.existsById(id)) {
            throw new RuntimeException("Dirección no encontrada con id: " + id);
        }
        direccionEnvioRepository.deleteById(id);
    }
}
