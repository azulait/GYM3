package gym.ms_asignacion.Service;

import gym.ms_asignacion.Model.Asignacion;
import gym.ms_asignacion.Repository.AsignacionRepository;
import gym.ms_asignacion.Client.EjercicioFeingClient;
import gym.ms_asignacion.Client.PlanNutricionalFeingClient;
import gym.ms_asignacion.Client.UsuarioFeingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    public Map<String, Object> obtenerAsignacionCompleta(int id) {
        Map<String, Object> respuesta = new HashMap<>();

        Asignacion asignacion = repository.findById(id).orElse(null);

        if (asignacion == null) {
            return respuesta;
        }

        respuesta.put("asignacionInfo", asignacion);

        respuesta.put("usuario", usuarioClient.obtenerUsuarioPorId(asignacion.getUsuarioId()));
        respuesta.put("planNutricional", nutricionClient.obtenerPlanPorId(asignacion.getPlanNutricionalId()));
        respuesta.put("ejercicio", ejercicioClient.obtenerEjercicioPorId(asignacion.getEjercicioId()));

        return respuesta;
    }

    public Asignacion guardar(Asignacion asignacion){
        return repository.save(asignacion);
    }
}
