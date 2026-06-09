package gym.ms_nutricion.Service;

import gym.ms_nutricion.Model.PlanNutricional;
import gym.ms_nutricion.Repository.PlanNutricionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanNutricionalService {
    @Autowired
    private PlanNutricionalRepository repository;


    public PlanNutricional findById(int id){
        return repository.findById(id).orElse(null);
    }

    public List<PlanNutricional> findAll() {
        return repository.findAll();
    }

    public PlanNutricional guardar(PlanNutricional entrenador) {
        return repository.save(entrenador);
    }

    public void eliminar(int id) {
        repository.deleteById(id);
    }
}
