package pe.Barberia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.Barberia.entities.Servicio;
import pe.Barberia.service.ServicioService;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public ResponseEntity<List<Servicio>> listarTodos() {
        return ResponseEntity.ok(servicioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> buscarPorId(@PathVariable Long id) {
        return servicioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar/{nombre}")
    public ResponseEntity<List<Servicio>> buscarPorNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(servicioService.buscarPorNombre(nombre));
    }

    @PostMapping("/agregar")
    public ResponseEntity<Servicio> registrar(@RequestBody Servicio servicio) {
        return ResponseEntity.ok(servicioService.registrar(servicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizar(@PathVariable Long id, @RequestBody Servicio servicio) {
        return ResponseEntity.ok(servicioService.actualizar(id, servicio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
