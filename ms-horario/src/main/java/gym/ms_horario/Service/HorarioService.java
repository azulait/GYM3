package gym.ms_horario.Service;

import gym.ms_horario.Model.Horario;
import gym.ms_horario.Repository.HorarioRepository;
import gym.ms_horario.Client.UsuarioFeignClient;
import gym.ms_horario.Client.EntrenadorFeignClient;
import gym.ms_horario.Model.DTO.UsuarioDTO;
import gym.ms_horario.Model.DTO.EntrenadorDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class HorarioService {

    @Autowired
    private HorarioRepository repository;

    @Autowired
    private UsuarioFeignClient usuarioClient;

    @Autowired
    private EntrenadorFeignClient entrenadorClient;

    public List<Horario> findAll() {
        log.debug("DEBUG: Consultando listado base de horarios.");
        return repository.findAll();
    }

    public Horario guardar(Horario horario) {
        log.info("INFORMACION: Guardando horario para la clase: {}", horario.getNombreClase());
        if (horario.getDetalleHorario() != null) {
            horario.getDetalleHorario().setHorario(horario);
        }
        return repository.save(horario);
    }

    public void eliminar(int id) {
        if (!repository.existsById(id)) {
            log.error("ERROR: Intento fallido de eliminar horario inexistente ID: {}", id);
            throw new RuntimeException("El horario no existe");
        }
        log.warn("ADVERTENCIA: Eliminando horario con ID: {}", id);
        repository.deleteById(id);
    }

    public Map<String, Object> obtenerReservaCompleta(int id) {
        log.info("INFORMACION: Orquestando datos del horario ID: {}", id);
        Map<String, Object> respuesta = new HashMap<>();

        Horario horario = repository.findById(id).orElse(null);
        if (horario == null) {
            log.error("ERROR: Horario no encontrado para ID: {}", id);
            return respuesta;
        }

        respuesta.put("claseInfo", horario);

        List<UsuarioDTO> listaAlumnos = new ArrayList<>();
        if (horario.getUsuariosIds() != null) {
            for (Integer usuarioId : horario.getUsuariosIds()) {
                try {
                    listaAlumnos.add(usuarioClient.obtenerUsuarioPorId(usuarioId));
                } catch (Exception e) {
                    log.warn("ADVERTENCIA: No se pudo contactar ms-usuario para ID: {}", usuarioId);
                    UsuarioDTO err = new UsuarioDTO();
                    err.setId(usuarioId); err.setNombre("No disponible");
                    listaAlumnos.add(err);
                }
            }
        }
        respuesta.put("alumnosInscritos", listaAlumnos);

        List<EntrenadorDTO> listaEntrenadores = new ArrayList<>();
        if (horario.getEntrenadoresIds() != null) {
            for (Integer entrenadorId : horario.getEntrenadoresIds()) {
                try {
                    listaEntrenadores.add(entrenadorClient.obtenerEntrenadorPorId(entrenadorId));
                } catch (Exception e) {
                    log.warn("ADVERTENCIA: No se pudo contactar ms-entrenador para ID: {}", entrenadorId);
                    EntrenadorDTO err = new EntrenadorDTO();
                    err.setId(entrenadorId); err.setNombre("No disponible");
                    listaEntrenadores.add(err);
                }
            }
        }
        respuesta.put("entrenadoresAsignados", listaEntrenadores);

        return respuesta;
    }
}