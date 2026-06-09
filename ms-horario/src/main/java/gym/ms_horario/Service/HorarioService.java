package gym.ms_horario.Service;

import gym.ms_horario.Model.Horario;
import gym.ms_horario.Repository.HorarioRepository;
import gym.ms_horario.Client.UsuarioFeignClient;
import gym.ms_horario.Client.EntrenadorFeignClient;
import gym.ms_horario.Model.DTO.UsuarioDTO;
import gym.ms_horario.Model.DTO.EntrenadorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HorarioService {

    @Autowired
    private HorarioRepository repository;

    @Autowired
    private UsuarioFeignClient usuarioClient;

    @Autowired
    private EntrenadorFeignClient entrenadorClient;

    public Horario guardar(Horario horario) {
        if (horario.getDetalleHorario() != null) {
            horario.getDetalleHorario().setHorario(horario);
        }
        return repository.save(horario);
    }

    public Map<String, Object> obtenerReservaCompleta(int id) {
        Map<String, Object> respuesta = new HashMap<>();

        Horario horario = repository.findById(id).orElse(null);
        if (horario == null) {
            return respuesta;
        }

        respuesta.put("claseInfo", horario);

        List<UsuarioDTO> listaAlumnos = new ArrayList<>();
        if (horario.getUsuariosIds() != null) {
            for (Integer usuarioId : horario.getUsuariosIds()) {
                try {
                    listaAlumnos.add(usuarioClient.obtenerUsuarioPorId(usuarioId));
                } catch (Exception e) {
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