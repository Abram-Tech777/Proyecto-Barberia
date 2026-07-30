package pe.Barberia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.Barberia.entities.Resenia;
import pe.Barberia.service.ReseniaService;

import java.util.List;

@RestController
@RequestMapping("/api/resenias")
public class ReseniaController {

    private final ReseniaService reseniaService;

    public ReseniaController(ReseniaService reseniaService) {
        this.reseniaService = reseniaService;
    }

    @GetMapping
    public ResponseEntity<List<Resenia>> listarTodos() {
        return ResponseEntity.ok(reseniaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resenia> buscarPorId(@PathVariable Long id) {
        return reseniaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Resenia>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(reseniaService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<Resenia>> listarPorProducto(@PathVariable Long productoId) {
        return ResponseEntity.ok(reseniaService.listarPorProducto(productoId));
    }

    @PostMapping("/agregar")
    public ResponseEntity<Resenia> registrar(@RequestBody Resenia resenia) {
        return ResponseEntity.ok(reseniaService.registrar(resenia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resenia> actualizar(@PathVariable Long id, @RequestBody Resenia resenia) {
        return ResponseEntity.ok(reseniaService.actualizar(id, resenia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        reseniaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}