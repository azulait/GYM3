package gym.ms_ejercicios;


import gym.ms_ejercicios.Model.Ejercicio;
import gym.ms_ejercicios.Repository.EjercicioRepository;
import gym.ms_ejercicios.Service.EjercicioService;
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
public class EjerciciosServicesTest {

    @InjectMocks
    private EjercicioService ejercicioService;

    @Mock
    private EjercicioRepository ejercicioRepository;

    @Test
    public void testObtenerTodos() {
        Ejercicio mock = new Ejercicio();
        mock.setGrupoMuscular("Pecho");

        when(ejercicioRepository.findAll()).thenReturn(List.of(mock));

        List<Ejercicio> resultados = ejercicioService.findAll();

        assertNotNull(resultados);
        assertEquals(1, resultados.size());
    }

    @Test
    public void testFindById() {
        int id = 1;
        Ejercicio mock = new Ejercicio();
        mock.setGrupoMuscular("Espalda");

        when(ejercicioRepository.findById(id)).thenReturn(Optional.of(mock));

        Ejercicio found = ejercicioService.findById(id);

        assertNotNull(found);
        assertEquals("Espalda", found.getGrupoMuscular());
    }

    @Test
    public void testGuardar() {
        Ejercicio nuevo = new Ejercicio();
        nuevo.setGrupoMuscular("Piernas");

        when(ejercicioRepository.save(any(Ejercicio.class))).thenReturn(nuevo);

        Ejercicio saved = ejercicioService.guardar(nuevo);

        assertNotNull(saved);
        assertEquals("Piernas", saved.getGrupoMuscular());
    }

    @Test
    public void testEliminar() {
        int id = 1;
        when(ejercicioRepository.existsById(id)).thenReturn(true);
        ejercicioService.eliminar(id);
        verify(ejercicioRepository, times(1)).deleteById(id);
    }
}