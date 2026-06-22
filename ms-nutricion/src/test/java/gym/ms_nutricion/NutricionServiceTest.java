package gym.ms_nutricion;


import gym.ms_nutricion.Model.PlanNutricional;
import gym.ms_nutricion.Repository.PlanNutricionalRepository;
import gym.ms_nutricion.Service.PlanNutricionalService;
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
public class NutricionServiceTest {

    @InjectMocks
    private PlanNutricionalService planService;

    @Mock
    private PlanNutricionalRepository planRepository;

    @Test
    public void testObtenerTodos() {
        PlanNutricional mockPlan = new PlanNutricional();
        mockPlan.setNombrePlan("Dieta Keto");

        when(planRepository.findAll()).thenReturn(List.of(mockPlan));

        List<PlanNutricional> resultados = planService.findAll();

        assertNotNull(resultados);
        assertEquals(1, resultados.size());
    }

    @Test
    public void testFindById() {
        int id = 1;
        PlanNutricional mockPlan = new PlanNutricional();
        mockPlan.setNombrePlan("Volumen");

        when(planRepository.findById(id)).thenReturn(Optional.of(mockPlan));

        PlanNutricional found = planService.findById(id);

        assertNotNull(found);
        assertEquals("Volumen", found.getNombrePlan());
    }

    @Test
    public void testGuardar() {
        PlanNutricional nuevoPlan = new PlanNutricional();
        nuevoPlan.setNombrePlan("Deficit");

        when(planRepository.save(any(PlanNutricional.class))).thenReturn(nuevoPlan);

        PlanNutricional saved = planService.guardar(nuevoPlan);

        assertNotNull(saved);
        assertEquals("Deficit", saved.getNombrePlan());
    }

    @Test
    public void testEliminar() {
        int id = 1;
        when(planRepository.existsById(id)).thenReturn(true);
        planService.eliminar(id);
        verify(planRepository, times(1)).deleteById(id);
    }
}
