package pe.Barberia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.Barberia.entities.TarjetaLealtad;
import pe.Barberia.service.TarjetaLealtadService;

import java.util.List;

@RestController
@RequestMapping("/api/tarjetas-lealtad")
public class TarjetaLealtadController {

    private final TarjetaLealtadService tarjetaLealtadService;

    public TarjetaLealtadController(TarjetaLealtadService tarjetaLealtadService) {
        this.tarjetaLealtadService = tarjetaLealtadService;
    }

    @GetMapping
    public ResponseEntity<List<TarjetaLealtad>> listarTodos() {
        return ResponseEntity.ok(tarjetaLealtadService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarjetaLealtad> buscarPorId(@PathVariable Long id) {
        return tarjetaLealtadService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/celular/{celular}")
    public ResponseEntity<TarjetaLealtad> buscarPorCelular(@PathVariable String celular) {
        return tarjetaLealtadService.buscarPorCelular(celular)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/registrar")
    public ResponseEntity<TarjetaLealtad> registrar(@RequestBody TarjetaLealtad tarjetaLealtad) {
        return ResponseEntity.ok(tarjetaLealtadService.registrar(tarjetaLealtad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarjetaLealtad> actualizar(@PathVariable Long id, @RequestBody TarjetaLealtad tarjetaLealtad) {
        return ResponseEntity.ok(tarjetaLealtadService.actualizar(id, tarjetaLealtad));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        tarjetaLealtadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}