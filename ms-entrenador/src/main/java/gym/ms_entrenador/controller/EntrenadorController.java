package gym.ms_entrenador.controller;

import gym.ms_entrenador.model.Entrenador;
import gym.ms_entrenador.service.EntrenadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/entrenadores")
public class EntrenadorController {

    @Autowired
    private EntrenadorService service;

    @GetMapping
    public List<Entrenador> listarTodos() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Entrenador buscarPorId(@PathVariable int id){
        return service.findById(id);
    }

    @PostMapping
    public Entrenador crearUsuario(@RequestBody Entrenador usuario) {
        return service.guardar(usuario);
    }

    @PutMapping("/{id}")
    public Entrenador actualizarUsuario(@RequestBody Entrenador usuario, @PathVariable int id) {
        usuario.setId(id);
        return service.guardar(usuario);
    }

    @DeleteMapping("/{id}") // Para borrar
    public void eliminarUsuario(@PathVariable int id) {
        service.eliminar(id);
    }
}