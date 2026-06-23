package gym.ms_horario.Controller;

import gym.ms_horario.Model.Horario;
import gym.ms_horario.Service.HorarioService;
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
@RequestMapping("/api/v1/horarios")
@Tag(name = "Gestión de Horarios", description = "Administra las clases, detalles, alumnos y entrenadores asignados")
public class HorarioController {

    @Autowired
    private HorarioService service;

    @GetMapping
    @Operation(summary = "Listar todos", description = "Retorna el registro básico de todos los horarios programados.")
    public ResponseEntity<List<Horario>> listarTodos() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Crear horario", description = "Registra un nuevo horario incluyendo su detalle, listas de alumnos y entrenadores.")
    public ResponseEntity<Horario> crear(@RequestBody Horario horario) {
        Horario nuevoHorario = service.guardar(horario);
        return new ResponseEntity<>(nuevoHorario, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener detalle orquestado", description = "Consume OpenFeign para obtener los datos de alumnos y entrenadores.")
    public ResponseEntity<Map<String, Object>> obtenerDetalleCompleto(@PathVariable int id) {
        Map<String, Object> respuesta = service.obtenerReservaCompleta(id);
        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar horario", description = "Borra físicamente el horario y su detalle asociado (Cascade).")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}