package gym.ms_nutricion;

import gym.ms_nutricion.Model.PlanNutricional;
import gym.ms_nutricion.Repository.PlanNutricionalRepository;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PlanNutricionalRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        List<PlanNutricional> planesFake = new ArrayList<>();

        String[] nombresPlanes = {"Volumen Limpio", "Déficit Calórico", "Mantenimiento", "Keto Extrema", "Ayuno Intermitente"};
        String[] macros = {"50% Carbs, 30% Prote, 20% Grasas", "40% Carbs, 40% Prote, 20% Grasas", "10% Carbs, 40% Prote, 50% Grasas"};

        for (int i = 0; i < 5; i++) {
            PlanNutricional plan = new PlanNutricional();

            plan.setNombrePlan(nombresPlanes[i]);
            plan.setCaloriasObjetivo(faker.number().numberBetween(1500, 3500));
            plan.setDescripcion(faker.lorem().sentence(10));
            plan.setMacroNutrientes(macros[faker.random().nextInt(macros.length)]);

            planesFake.add(plan);
        }

        repository.saveAll(planesFake);
    }
}