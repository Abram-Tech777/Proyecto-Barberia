package pe.Barberia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.Barberia.entities.Barbero;
import pe.Barberia.service.BarberoService;

import java.util.List;

@RestController
@RequestMapping("/api/barberos")
public class BarberoController {

    private final BarberoService barberoService;

    public BarberoController(BarberoService barberoService) {
        this.barberoService = barberoService;
    }

    @GetMapping
    public ResponseEntity<List<Barbero>> listarTodos() {
        return ResponseEntity.ok(barberoService.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Barbero>> listarActivos() {
        return ResponseEntity.ok(barberoService.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barbero> buscarPorId(@PathVariable Long id) {
        return barberoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/registrar")
    public ResponseEntity<Barbero> registrar(@RequestBody Barbero barbero) {
        return ResponseEntity.ok(barberoService.registrar(barbero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbero> actualizar(@PathVariable Long id, @RequestBody Barbero barbero) {
        return ResponseEntity.ok(barberoService.actualizar(id, barbero));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        barberoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
