package pe.Barberia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.Barberia.entities.Venta;
import pe.Barberia.enums.EstadoPedido;
import pe.Barberia.service.VentaService;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listarTodos() {
        return ResponseEntity.ok(ventaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> buscarPorId(@PathVariable Long id) {
        return ventaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/comprador/{compradorId}")
    public ResponseEntity<List<Venta>> listarPorComprador(@PathVariable Long compradorId) {
        return ResponseEntity.ok(ventaService.listarPorComprador(compradorId));
    }

    @GetMapping("/cita/{citaId}")
    public ResponseEntity<List<Venta>> listarPorCita(@PathVariable Long citaId) {
        return ResponseEntity.ok(ventaService.listarPorCita(citaId));
    }

    @GetMapping("/estado/{estadoPedido}")
    public ResponseEntity<List<Venta>> listarPorEstado(@PathVariable EstadoPedido estadoPedido) {
        return ResponseEntity.ok(ventaService.listarPorEstado(estadoPedido));
    }

    @PostMapping
    public ResponseEntity<Venta> registrar(@RequestBody Venta venta) {
        return ResponseEntity.ok(ventaService.registrar(venta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> actualizar(@PathVariable Long id, @RequestBody Venta venta) {
        return ResponseEntity.ok(ventaService.actualizar(id, venta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}