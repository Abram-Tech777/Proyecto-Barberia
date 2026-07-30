package pe.Barberia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.Barberia.entities.Promocion;
import pe.Barberia.service.PromocionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/promociones")
public class PromocionController {

    private final PromocionService promocionService;

    public PromocionController(PromocionService promocionService) {
        this.promocionService = promocionService;
    }

    @GetMapping
    public ResponseEntity<List<Promocion>> listarTodos() {
        return ResponseEntity.ok(promocionService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promocion> buscarPorId(@PathVariable Long id) {
        return promocionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Promocion>> listarActivos() {
        return ResponseEntity.ok(promocionService.listarActivos());
    }

    @GetMapping("/vigentes")
    public ResponseEntity<List<Promocion>> listarVigentes(@RequestParam LocalDate fecha) {
        return ResponseEntity.ok(promocionService.listarVigentes(fecha));
    }

    @PostMapping
    public ResponseEntity<Promocion> registrar(@RequestBody Promocion promocion) {
        return ResponseEntity.ok(promocionService.registrar(promocion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promocion> actualizar(@PathVariable Long id, @RequestBody Promocion promocion) {
        return ResponseEntity.ok(promocionService.actualizar(id, promocion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        promocionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}