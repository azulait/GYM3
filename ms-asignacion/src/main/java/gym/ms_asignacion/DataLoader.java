package gym.ms_asignacion;

import gym.ms_asignacion.Model.Asignacion;
import gym.ms_asignacion.Repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AsignacionRepository repository;

    @Override
    public void run(String... args) throws Exception {
        List<Asignacion> asignacionesFake = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= 5; i++) {
            Asignacion asignacion = new Asignacion();
            asignacion.setUsuarioId(i);
            asignacion.setPlanNutricionalId(random.nextInt(5) + 1);
            asignacion.setEjercicioId(random.nextInt(6) + 1);

            asignacionesFake.add(asignacion);
        }

        repository.saveAll(asignacionesFake);
    }
}