package pe.Barberia.service;

import org.springframework.stereotype.Service;
import pe.Barberia.entities.Venta;
import pe.Barberia.enums.EstadoPedido;
import pe.Barberia.repositories.VentaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;

    public VentaService(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> listarTodos() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }

    public List<Venta> listarPorComprador(Long compradorId) {
        return ventaRepository.findByCompradorId(compradorId);
    }

    public List<Venta> listarPorCita(Long citaId) {
        return ventaRepository.findByCitaAsociadaId(citaId);
    }

    public List<Venta> listarPorEstado(EstadoPedido estadoPedido) {
        return ventaRepository.findByEstadoPedido(estadoPedido);
    }

    public Venta registrar(Venta venta) {
        venta.setFechaTransaccion(LocalDateTime.now());
        return ventaRepository.save(venta);
    }

    public Venta actualizar(Long id, Venta ventaActualizada) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));

        venta.setComprador(ventaActualizada.getComprador());
        venta.setCitaAsociada(ventaActualizada.getCitaAsociada());
        venta.setMontoTotal(ventaActualizada.getMontoTotal());
        venta.setMedioPago(ventaActualizada.getMedioPago());
        venta.setTipoDespacho(ventaActualizada.getTipoDespacho());
        venta.setOrigenOrden(ventaActualizada.getOrigenOrden());
        venta.setDireccionEnvio(ventaActualizada.getDireccionEnvio());
        venta.setCoordenadasEnvio(ventaActualizada.getCoordenadasEnvio());
        venta.setReferenciaDireccion(ventaActualizada.getReferenciaDireccion());
        venta.setCostoEnvio(ventaActualizada.getCostoEnvio());
        venta.setEstadoPedido(ventaActualizada.getEstadoPedido());

        return ventaRepository.save(venta);
    }

    public void eliminar(Long id) {
        if (!ventaRepository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con id: " + id);
        }
        ventaRepository.deleteById(id);
    }
}