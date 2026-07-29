package pe.Barberia.service;

import org.springframework.stereotype.Service;
import pe.Barberia.entities.TarjetaLealtad;
import pe.Barberia.repositories.TarjetaLealtadRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TarjetaLealtadService {

    private final TarjetaLealtadRepository tarjetaLealtadRepository;

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
        return tarjetaLealtadRepository.findByCelularCliente(celularCliente);
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