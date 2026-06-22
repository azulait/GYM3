package gym.ms_asignacion.Service;

import gym.ms_asignacion.Model.Asignacion;
import gym.ms_asignacion.Repository.AsignacionRepository;
import gym.ms_asignacion.Client.EjercicioFeingClient;
import gym.ms_asignacion.Client.PlanNutricionalFeingClient;
import gym.ms_asignacion.Client.UsuarioFeingClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AsignacionService {

    @Autowired
    private AsignacionRepository repository;

    @Autowired
    private UsuarioFeingClient usuarioClient;

    @Autowired
    private PlanNutricionalFeingClient nutricionClient;

    @Autowired
    private EjercicioFeingClient ejercicioClient;

    public List<Asignacion> findAll() {
        log.debug("DEBUG: Solicitando lista de todas las asignaciones base.");
        return repository.findAll();
    }

    public Map<String, Object> obtenerAsignacionCompleta(int id) {
        log.info("INFORMACION: Orquestando datos para la asignación ID: {}", id);
        Map<String, Object> respuesta = new HashMap<>();

        Asignacion asignacion = repository.findById(id).orElse(null);

        if (asignacion == null) {
            log.error("ERROR: No se encontró la asignación con ID: {}", id);
            return respuesta;
        }

        respuesta.put("asignacionInfo", asignacion);

        log.debug("DEBUG: Consumiendo ms-usuario, ms-nutricion y ms-ejercicios vía Feign...");
        respuesta.put("usuario", usuarioClient.obtenerUsuarioPorId(asignacion.getUsuarioId()));
        respuesta.put("planNutricional", nutricionClient.obtenerPlanPorId(asignacion.getPlanNutricionalId()));
        respuesta.put("ejercicio", ejercicioClient.obtenerEjercicioPorId(asignacion.getEjercicioId()));

        return respuesta;
    }

    public Asignacion guardar(Asignacion asignacion){
        log.info("INFORMACION: Guardando nueva asignación (Usuario ID: {})", asignacion.getUsuarioId());
        return repository.save(asignacion);
    }

    public void eliminar(int id) {
        if(!repository.existsById(id)){
            log.error("ERROR: Intento fallido de eliminar asignación inexistente con ID: {}", id);
            throw new RuntimeException("La asignación no existe");
        }
        log.warn("ADVERTENCIA: Eliminando asignación ID: {}", id);
        repository.deleteById(id);
    }
}