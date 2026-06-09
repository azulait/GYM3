package gym.ms_nutricion.Controller;

import gym.ms_nutricion.Model.PlanNutricional;
import gym.ms_nutricion.Service.PlanNutricionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/nutricion")
public class PlanNutricionalController {
    @Autowired
    private PlanNutricionalService service;

    @GetMapping
    public List<PlanNutricional> listarTodos() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public PlanNutricional buscarPorId(@PathVariable int id){
        return service.findById(id);
    }

    @PostMapping
    public PlanNutricional crearUsuario(@RequestBody PlanNutricional usuario) {
        return service.guardar(usuario);
    }

    @PutMapping("/{id}")
    public PlanNutricional actualizarUsuario(@RequestBody PlanNutricional usuario, @PathVariable int id) {
        usuario.setId(id);
        return service.guardar(usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable int id) {
        service.eliminar(id);
    }
}
