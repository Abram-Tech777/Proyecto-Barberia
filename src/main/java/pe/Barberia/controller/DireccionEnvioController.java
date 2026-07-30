package pe.Barberia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.Barberia.entities.DireccionEnvio;
import pe.Barberia.service.DireccionEnvioService;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionEnvioController {

    private final DireccionEnvioService direccionEnvioService;

    public DireccionEnvioController(DireccionEnvioService direccionEnvioService) {
        this.direccionEnvioService = direccionEnvioService;
    }

    @GetMapping
    public ResponseEntity<List<DireccionEnvio>> listarTodos() {
        return ResponseEntity.ok(direccionEnvioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionEnvio> buscarPorId(@PathVariable Long id) {
        return direccionEnvioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<DireccionEnvio>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(direccionEnvioService.listarPorUsuario(usuarioId));
    }

    @PostMapping("/agregar")
    public ResponseEntity<DireccionEnvio> registrar(@RequestBody DireccionEnvio direccionEnvio) {
        return ResponseEntity.ok(direccionEnvioService.registrar(direccionEnvio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionEnvio> actualizar(@PathVariable Long id,
                                                     @RequestBody DireccionEnvio direccionEnvio) {
        return ResponseEntity.ok(direccionEnvioService.actualizar(id, direccionEnvio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        direccionEnvioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
