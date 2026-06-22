package gym.ms_ejercicios.Controller;

import gym.ms_ejercicios.Model.Ejercicio;
import gym.ms_ejercicios.Service.EjercicioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/ejercicios")
@Tag(name = "CRUD Ejercicios", description = "Gestión de rutinas y grupos musculares")
public class EjercicioController {

    @Autowired
    private EjercicioService service;

    @GetMapping
    @Operation(summary = "Obtener todos los ejercicios", description = "Retorna el listado de grupos musculares y cantidad de ejercicios.")
    public List<Ejercicio> listarTodos() {
        log.debug("DEBUG: Controlador solicita listado de ejercicios.");
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar ejercicio por ID", description = "Retorna los detalles de un registro de ejercicio específico.")
    public Ejercicio buscarPorId(@PathVariable int id){
        log.info("INFORMACION: Solicitando información del ejercicio con id=" + id);
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear nuevo registro de ejercicio", description = "Guarda un nuevo grupo muscular en la base de datos.")
    public Ejercicio crearEjercicio(@RequestBody Ejercicio ejercicio) {
        log.info("INFORMACION: Creando registro para el grupo: {}", ejercicio.getGrupoMuscular());
        return service.guardar(ejercicio);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar registro", description = "Modifica un registro de ejercicio existente.")
    public Ejercicio actualizarEjercicio(@RequestBody Ejercicio ejercicio, @PathVariable int id) {
        log.info("INFORMACION: Actualizando ejercicio con ID: {}", id);
        ejercicio.setId(id);
        return service.guardar(ejercicio);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar ejercicio", description = "Borra físicamente un registro de la base de datos.")
    public void eliminarEjercicio(@PathVariable int id) {
        log.warn("ADVERTENCIA: Eliminando registro de ejercicio con ID: {}", id);
        service.eliminar(id);
    }
}