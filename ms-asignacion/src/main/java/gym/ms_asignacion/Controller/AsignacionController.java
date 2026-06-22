package gym.ms_asignacion.Controller;

import gym.ms_asignacion.Model.Asignacion;
import gym.ms_asignacion.Service.AsignacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/asignaciones")
@Tag(name = "Orquestador de Asignaciones", description = "Asigna rutinas y dietas a los usuarios integrando varios microservicios")
public class AsignacionController {

    @Autowired
    private AsignacionService service;

    @GetMapping
    @Operation(summary = "Obtener todas las asignaciones", description = "Retorna el listado base de asignaciones registradas.")
    public ResponseEntity<List<Asignacion>> listarTodos() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener asignación completa", description = "Usa OpenFeign para traer los datos del Usuario, su Plan Nutricional y sus Ejercicios.")
    public ResponseEntity<Map<String, Object>> obtenerDetalleCompleto(@PathVariable int id) {
        Map<String, Object> respuesta = service.obtenerAsignacionCompleta(id);

        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(respuesta);
    }

    @PostMapping
    @Operation(summary = "Crear nueva asignación", description = "Vincula los IDs de un usuario, un plan y un ejercicio.")
    public ResponseEntity<Asignacion> crear(@RequestBody Asignacion asignacion) {
        Asignacion nuevaAsignacion = service.guardar(asignacion);
        return new ResponseEntity<>(nuevaAsignacion, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar asignación", description = "Borra el vínculo de asignación.")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}