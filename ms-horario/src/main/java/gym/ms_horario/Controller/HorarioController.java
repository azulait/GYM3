package gym.ms_horario.Controller;

import gym.ms_horario.Model.Horario;
import gym.ms_horario.Service.HorarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/horarios")
public class HorarioController {

    @Autowired
    private HorarioService service;

    @PostMapping
    public ResponseEntity<Horario> crear(@RequestBody Horario horario) {
        Horario nuevoHorario = service.guardar(horario);
        return new ResponseEntity<>(nuevoHorario, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> obtenerDetalleCompleto(@PathVariable int id) {
        Map<String, Object> respuesta = service.obtenerReservaCompleta(id);

        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(respuesta);
    }
}