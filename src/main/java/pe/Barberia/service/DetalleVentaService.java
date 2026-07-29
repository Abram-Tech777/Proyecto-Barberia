package pe.Barberia.service;

import org.springframework.stereotype.Service;
import pe.Barberia.entities.DetalleVenta;
import pe.Barberia.repositories.DetalleVentaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleVentaService {

    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    public List<DetalleVenta> listarTodos() {
        return detalleVentaRepository.findAll();
    }

    public Optional<DetalleVenta> buscarPorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    public List<DetalleVenta> listarPorVenta(Long ventaId) {
        return detalleVentaRepository.findByVentaId(ventaId);
    }

    public DetalleVenta registrar(DetalleVenta detalleVenta) {
        return detalleVentaRepository.save(detalleVenta);
    }

    public DetalleVenta actualizar(Long id, DetalleVenta detalleActualizado) {
        DetalleVenta detalle = detalleVentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Detalle de venta no encontrado con id: " + id));

        detalle.setVenta(detalleActualizado.getVenta());
        detalle.setProducto(detalleActualizado.getProducto());
        detalle.setCantidad(detalleActualizado.getCantidad());
        detalle.setPrecioUnitario(detalleActualizado.getPrecioUnitario());

        return detalleVentaRepository.save(detalle);
    }

    public void eliminar(Long id) {
        if (!detalleVentaRepository.existsById(id)) {
            throw new RuntimeException("Detalle de venta no encontrado con id: " + id);
        }
        detalleVentaRepository.deleteById(id);
    }
}