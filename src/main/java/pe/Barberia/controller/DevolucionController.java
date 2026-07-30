package pe.Barberia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.Barberia.entities.Devolucion;
import pe.Barberia.service.DevolucionService;

import java.util.List;

@RestController
@RequestMapping("/api/devoluciones")
public class DevolucionController {

    private final DevolucionService devolucionService;

    public DevolucionController(DevolucionService devolucionService) {
        this.devolucionService = devolucionService;
    }

    @GetMapping
    public ResponseEntity<List<Devolucion>> listarTodos() {
        return ResponseEntity.ok(devolucionService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Devolucion> buscarPorId(@PathVariable Long id) {
        return devolucionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cita/{citaId}")
    public ResponseEntity<Devolucion> buscarPorCita(@PathVariable Long citaId) {
        return devolucionService.buscarPorCita(citaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/celular/{celular}")
    public ResponseEntity<List<Devolucion>> listarPorCelular(@PathVariable String celular) {
        return ResponseEntity.ok(devolucionService.listarPorCelular(celular));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Devolucion> registrar(@RequestBody Devolucion devolucion) {
        return ResponseEntity.ok(devolucionService.registrar(devolucion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Devolucion> actualizar(@PathVariable Long id, @RequestBody Devolucion devolucion) {
        return ResponseEntity.ok(devolucionService.actualizar(id, devolucion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        devolucionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}