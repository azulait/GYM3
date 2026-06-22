package gym.ms_entrenador.controller;

import gym.ms_entrenador.model.Entrenador;
import gym.ms_entrenador.service.EntrenadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/entrenadores")
@Tag(name = "CRUD Entrenadores", description = "Gestión del personal técnico y entrenadores del gimnasio")
public class EntrenadorController {

    @Autowired
    private EntrenadorService service;

    @GetMapping
    @Operation(summary = "Obtener a todos los entrenadores", description = "Retorna una lista completa de todos los entrenadores registrados.")
    public List<Entrenador> listarTodos() {
        log.debug("DEBUG: Se encontraron los siguientes entrenadores");
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un entrenador por ID", description = "Retorna los datos de un entrenador si coincide el ID con la base de datos.")
    public Entrenador buscarPorId(@PathVariable int id){
        log.info("INFORMACION: Administrador solicita información de entrenador con id=" + id);
        return service.findById(id);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo entrenador", description = "Registra un entrenador en la base de datos y genera su ID único.")
    public Entrenador crearEntrenador(@RequestBody Entrenador entrenador) {
        log.info("INFORMACION: Creando nuevo entrenador con nombre: {}", entrenador.getNombre());
        return service.guardar(entrenador);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar entrenador existente", description = "Modifica los datos de un entrenador buscando por su ID.")
    public Entrenador actualizarEntrenador(@RequestBody Entrenador entrenador, @PathVariable int id) {
        log.info("INFORMACION: Actualizando entrenador con ID: {}", id);
        entrenador.setId(id);
        return service.guardar(entrenador);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un entrenador", description = "Borra físicamente el registro del entrenador mediante su ID.")
    public void eliminarEntrenador(@PathVariable int id) {
        log.warn("ADVERTENCIA: Eliminando entrenador con ID: {}", id);
        service.eliminar(id);
    }
}