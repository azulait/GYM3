package gym.ms_entrenador;

import gym.ms_entrenador.model.Entrenador;
import gym.ms_entrenador.repository.EntrenadorRepository;
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
    private EntrenadorRepository entrenadorRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        List<Entrenador> entrenadoresFake = new ArrayList<>();
        String[] especialidades = {"Musculación", "Crossfit", "Yoga", "Pilates", "Zumba", "Spinning"};
        Character[] generos = {'M', 'F'};

        for (int i = 0; i < 10; i++) {
            Entrenador entrenador = new Entrenador();

            entrenador.setNombre(faker.name().fullName());
            entrenador.setEdad(faker.number().numberBetween(20, 55));
            entrenador.setGenero(generos[faker.random().nextInt(generos.length)]);
            entrenador.setEspecialidad(especialidades[faker.random().nextInt(especialidades.length)]);

            entrenadoresFake.add(entrenador);
        }

        entrenadorRepository.saveAll(entrenadoresFake);
    }
}