package gym.ms_ejercicios.Controller;

import gym.ms_ejercicios.Model.Ejercicio;
import gym.ms_ejercicios.Service.EjercicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ejercicios")
public class EjercicioController {
    @Autowired
    private EjercicioService service;

    @GetMapping
    public List<Ejercicio> listarTodos() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Ejercicio buscarPorId(@PathVariable int id){
        return service.findById(id);
    }

    @PostMapping
    public Ejercicio crearUsuario(@RequestBody Ejercicio usuario) {
        return service.guardar(usuario);
    }

    @PutMapping("/{id}")
    public Ejercicio actualizarUsuario(@RequestBody Ejercicio usuario, @PathVariable int id) {
        usuario.setId(id);
        return service.guardar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable int id) {
        service.eliminar(id);
    }
}
