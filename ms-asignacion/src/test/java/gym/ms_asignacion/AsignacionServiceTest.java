package gym.ms_asignacion;

import gym.ms_asignacion.Model.Asignacion;
import gym.ms_asignacion.Model.DTO.EjercicioDTO;
import gym.ms_asignacion.Model.DTO.PlanNutricionalDTO;
import gym.ms_asignacion.Model.DTO.UsuarioDTO;
import gym.ms_asignacion.Repository.AsignacionRepository;
import gym.ms_asignacion.Service.AsignacionService;
import gym.ms_asignacion.Client.UsuarioFeingClient;
import gym.ms_asignacion.Client.PlanNutricionalFeingClient;
import gym.ms_asignacion.Client.EjercicioFeingClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AsignacionServiceTest {

    @InjectMocks
    private AsignacionService asignacionService;

    @Mock
    private AsignacionRepository asignacionRepository;

    @Mock
    private UsuarioFeingClient usuarioClient;

    @Mock
    private PlanNutricionalFeingClient nutricionClient;

    @Mock
    private EjercicioFeingClient ejercicioClient;

    @Test
    public void testObtenerAsignacionCompleta_Exitoso() {
        Asignacion mockAsignacion = new Asignacion();
        mockAsignacion.setId(1);
        mockAsignacion.setUsuarioId(10);
        mockAsignacion.setPlanNutricionalId(20);
        mockAsignacion.setEjercicioId(30);

        when(asignacionRepository.findById(1)).thenReturn(Optional.of(mockAsignacion));

        when(usuarioClient.obtenerUsuarioPorId(10)).thenReturn(new UsuarioDTO());
        when(nutricionClient.obtenerPlanPorId(20)).thenReturn(new PlanNutricionalDTO());
        when(ejercicioClient.obtenerEjercicioPorId(30)).thenReturn(new EjercicioDTO());

        Map<String, Object> resultado = asignacionService.obtenerAsignacionCompleta(1);

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.containsKey("usuario"));
        assertTrue(resultado.containsKey("planNutricional"));
        assertTrue(resultado.containsKey("ejercicio"));
    }

    @Test
    public void testObtenerAsignacionCompleta_NoEncontrado() {
        when(asignacionRepository.findById(99)).thenReturn(Optional.empty());

        Map<String, Object> resultado = asignacionService.obtenerAsignacionCompleta(99);

        assertTrue(resultado.isEmpty());
    }
}