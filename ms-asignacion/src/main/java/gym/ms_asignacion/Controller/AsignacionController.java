package gym.ms_asignacion.Controller;

import gym.ms_asignacion.Model.Asignacion;
import gym.ms_asignacion.Service.AsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/asignaciones")
public class AsignacionController {

    @Autowired
    private AsignacionService service;

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerDetalleCompleto(@PathVariable int id) {
        Map<String, Object> respuesta = service.obtenerAsignacionCompleta(id);

        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    public ResponseEntity<Asignacion> crear(@RequestBody Asignacion asignacion) {
        Asignacion nuevaAsignacion = service.guardar(asignacion);
        return new ResponseEntity<>(nuevaAsignacion, HttpStatus.CREATED);

    }
}