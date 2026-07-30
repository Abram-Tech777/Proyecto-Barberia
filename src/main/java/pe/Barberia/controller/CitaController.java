package pe.Barberia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.Barberia.entities.Cita;
import pe.Barberia.service.CitaService;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public ResponseEntity<List<Cita>> listarTodos() {
        return ResponseEntity.ok(citaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarPorId(@PathVariable Long id) {
        return citaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/barbero/{barberoId}")
    public ResponseEntity<List<Cita>> listarPorBarbero(@PathVariable Long barberoId) {
        return ResponseEntity.ok(citaService.listarPorBarbero(barberoId));
    }

    @GetMapping("/celular/{celular}")
    public ResponseEntity<List<Cita>> listarPorCelular(@PathVariable String celular) {
        return ResponseEntity.ok(citaService.listarPorCelular(celular));
    }

    @GetMapping("/pendientes")
    public ResponseEntity<List<Cita>> listarPendientesValidar() {
        return ResponseEntity.ok(citaService.listarPendientesValidar());
    }

    @PostMapping("/reservar")
    public ResponseEntity<Cita> registrar(@RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.registrar(cita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(@PathVariable Long id, @RequestBody Cita cita) {
        return ResponseEntity.ok(citaService.actualizar(id, cita));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/cancelar-trolls")
    public ResponseEntity<Integer> cancelarTrolls() {
        int canceladas = citaService.cancelarSpam();
        return ResponseEntity.ok(canceladas);
    }
}
