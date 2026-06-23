package gym.ms_horario;

import gym.ms_horario.Model.Horario;
import gym.ms_horario.Repository.HorarioRepository;
import gym.ms_horario.Service.HorarioService;
import gym.ms_horario.Client.UsuarioFeignClient;
import gym.ms_horario.Client.EntrenadorFeignClient;
import gym.ms_horario.Model.DTO.UsuarioDTO;
import gym.ms_horario.Model.DTO.EntrenadorDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HorarioServiceTest {

    @InjectMocks
    private HorarioService horarioService;

    @Mock
    private HorarioRepository horarioRepository;

    @Mock
    private UsuarioFeignClient usuarioClient;

    @Mock
    private EntrenadorFeignClient entrenadorClient;

    @Test
    public void testObtenerReservaCompleta_Exitoso() {
        Horario mock = new Horario();
        mock.setId(1);
        mock.setUsuariosIds(Arrays.asList(10));
        mock.setEntrenadoresIds(Arrays.asList(20));

        when(horarioRepository.findById(1)).thenReturn(Optional.of(mock));

        when(usuarioClient.obtenerUsuarioPorId(10)).thenReturn(new UsuarioDTO());
        when(entrenadorClient.obtenerEntrenadorPorId(20)).thenReturn(new EntrenadorDTO());

        Map<String, Object> resultado = horarioService.obtenerReservaCompleta(1);

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.containsKey("alumnosInscritos"));
        assertTrue(resultado.containsKey("entrenadoresAsignados"));
    }

    @Test
    public void testGuardar_Exitoso() {
        Horario h = new Horario();
        h.setNombreClase("Pilates");

        when(horarioRepository.save(any(Horario.class))).thenReturn(h);

        Horario guardado = horarioService.guardar(h);
        assertNotNull(guardado);
        assertEquals("Pilates", guardado.getNombreClase());
    }

    @Test
    public void testEliminar() {
        int id = 1;
        when(horarioRepository.existsById(id)).thenReturn(true);
        horarioService.eliminar(id);
        verify(horarioRepository, times(1)).deleteById(id);
    }
}
