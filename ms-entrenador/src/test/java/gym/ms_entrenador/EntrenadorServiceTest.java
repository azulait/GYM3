package gym.ms_entrenador;

import gym.ms_entrenador.model.Entrenador;
import gym.ms_entrenador.repository.EntrenadorRepository;
import gym.ms_entrenador.service.EntrenadorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EntrenadorServiceTest {

    @InjectMocks
    private EntrenadorService entrenadorService;

    @Mock
    private EntrenadorRepository entrenadorRepository;

    @Test
    public void testObtenerTodos() {
        Entrenador entrenadorMock = new Entrenador();
        entrenadorMock.setNombre("Roberto Power");

        when(entrenadorRepository.findAll()).thenReturn(List.of(entrenadorMock));

        List<Entrenador> entrenadores = entrenadorService.findAll();

        assertNotNull(entrenadores);
        assertEquals(1, entrenadores.size());
    }

    @Test
    public void testFindById() {
        int id = 1;
        Entrenador entrenadorMock = new Entrenador();
        entrenadorMock.setNombre("María Fit");

        when(entrenadorRepository.findById(id)).thenReturn(Optional.of(entrenadorMock));

        Entrenador found = entrenadorService.findById(id);

        assertNotNull(found);
        assertEquals("María Fit", found.getNombre());
    }

    @Test
    public void testGuardar() {
        Entrenador nuevoEntrenador = new Entrenador();
        nuevoEntrenador.setNombre("Carlos Gym");
        nuevoEntrenador.setEspecialidad("Crossfit");

        when(entrenadorRepository.save(any(Entrenador.class))).thenReturn(nuevoEntrenador);

        Entrenador saved = entrenadorService.guardar(nuevoEntrenador);

        assertNotNull(saved);
        assertEquals("Carlos Gym", saved.getNombre());
        assertEquals("Crossfit", saved.getEspecialidad());
    }

    @Test
    public void testEliminar() {
        int id = 1;
        when(entrenadorRepository.existsById(id)).thenReturn(true);

        entrenadorService.eliminar(id);

        verify(entrenadorRepository, times(1)).deleteById(id);
    }
}
