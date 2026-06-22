package gym.ms_nutricion.Service;

import gym.ms_nutricion.Model.PlanNutricional;
import gym.ms_nutricion.Repository.PlanNutricionalRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PlanNutricionalService {

    @Autowired
    private PlanNutricionalRepository repository;

    public PlanNutricional findById(int id){
        return repository.findById(id).orElseThrow(() -> {
            log.error("ERROR: No se encontró el plan nutricional con ID: {}", id);
            return new RuntimeException("Plan nutricional no encontrado");
        });
    }

    public List<PlanNutricional> findAll() {
        log.debug("DEBUG: Obteniendo la lista completa de planes nutricionales.");
        return repository.findAll();
    }

    public PlanNutricional guardar(PlanNutricional plan) {
        log.info("INFORMACION: Guardando el plan nutricional: {}", plan.getNombrePlan());
        return repository.save(plan);
    }

    public void eliminar(int id) {
        if(!repository.existsById(id)){
            log.error("ERROR: Intento fallido de eliminar un plan inexistente con ID: {}", id);
            throw new RuntimeException("El plan nutricional no existe");
        }
        log.warn("ADVERTENCIA: Eliminando permanentemente el plan nutricional con ID: {}", id);
        repository.deleteById(id);
    }
}