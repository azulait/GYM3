package gym.ms_ejercicios;

import gym.ms_ejercicios.Model.Ejercicio;
import gym.ms_ejercicios.Repository.EjercicioRepository;
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
    private EjercicioRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        List<Ejercicio> ejerciciosFake = new ArrayList<>();

        String[] grupos = {"Pecho", "Espalda", "Piernas", "Hombros", "Brazos (Bíceps/Tríceps)", "Core/Abdomen"};

        for (String grupo : grupos) {
            Ejercicio ejercicio = new Ejercicio();
            ejercicio.setGrupoMuscular(grupo);
            ejercicio.setCantidadEjercicios(faker.number().numberBetween(4, 9));
            ejerciciosFake.add(ejercicio);
        }

        repository.saveAll(ejerciciosFake);
    }
}
