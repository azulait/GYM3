package gym.ms_nutricion.Controller;

import gym.ms_nutricion.Model.PlanNutricional;
import gym.ms_nutricion.Service.PlanNutricionalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/nutricion")
@Tag(name = "CRUD Nutrición", description = "Gestión de planes nutricionales y dietas del gimnasio")
public class PlanNutricionalController {

    @Autowired
    private PlanNutricionalService service;

    @GetMapping
    @Operation(summary = "Obtener todos los planes", description = "Retorna una lista completa de los planes nutricionales disponibles.")
    public List<PlanNutricional> listarTodos() {
        log.debug("DEBUG: Controlador solicita listado de planes nutricionales");
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un plan por ID", description = "Retorna los detalles de un plan nutricional específico.")
    public PlanNutricional buscarPorId(@PathVariable int id){
        log.info("INFORMACION: Solicitando información del plan nutricional con id=" + id);
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo plan", description = "Registra un nuevo plan nutricional en el sistema.")
    public PlanNutricional crearPlan(@RequestBody PlanNutricional plan) {
        log.info("INFORMACION: Creando nuevo plan nutricional: {}", plan.getNombrePlan());
        return service.guardar(plan);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar plan existente", description = "Modifica los datos de un plan buscando por su ID.")
    public PlanNutricional actualizarPlan(@RequestBody PlanNutricional plan, @PathVariable int id) {
        log.info("INFORMACION: Actualizando plan nutricional con ID: {}", id);
        plan.setId(id);
        return service.guardar(plan);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un plan", description = "Borra físicamente el registro del plan nutricional mediante su ID.")
    public void eliminarPlan(@PathVariable int id) {
        log.warn("ADVERTENCIA: Eliminando plan nutricional con ID: {}", id);
        service.eliminar(id);
    }
}