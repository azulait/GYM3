package gym.ms_horario;

import gym.ms_horario.Model.DetalleHorario;
import gym.ms_horario.Model.Horario;
import gym.ms_horario.Repository.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private HorarioRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Horario h1 = new Horario();
        h1.setNombreClase("Crossfit Extremo");
        h1.setDiaSemana("Lunes");
        h1.setHoraInicio(LocalTime.of(18, 0));
        h1.setUsuariosIds(Arrays.asList(1, 2, 3));
        h1.setEntrenadoresIds(Arrays.asList(1));

        DetalleHorario d1 = new DetalleHorario();
        d1.setCapacidadMaxima(20);
        d1.setHorario(h1);
        h1.setDetalleHorario(d1);

        Horario h2 = new Horario();
        h2.setNombreClase("Yoga Relax");
        h2.setDiaSemana("Martes");
        h2.setHoraInicio(LocalTime.of(8, 30));
        h2.setUsuariosIds(Arrays.asList(4, 5));
        h2.setEntrenadoresIds(Arrays.asList(2));

        DetalleHorario d2 = new DetalleHorario();
        d2.setCapacidadMaxima(15);
        d2.setHorario(h2);
        h2.setDetalleHorario(d2);

        repository.saveAll(List.of(h1, h2));
    }
}
