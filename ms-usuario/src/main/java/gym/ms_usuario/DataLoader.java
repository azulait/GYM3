package gym.ms_usuario;

import gym.ms_usuario.Model.*;
import gym.ms_usuario.Repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args)throws Exception{
        Faker faker = new Faker();
        List<Usuario> usuariosFake = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Usuario usuario = new Usuario();

            usuario.setNombre(faker.name().fullName());
            usuario.setEmail(faker.internet().emailAddress());
            usuario.setTelefono(faker.phoneNumber().cellPhone());

            LocalDate fechaNacimiento = faker.timeAndDate().birthday(18, 60);
            usuario.setFechaNacimiento(fechaNacimiento);

            usuario.setContactoEmergencia(faker.phoneNumber().cellPhone());

            usuariosFake.add(usuario);
        }

        usuarioRepository.saveAll(usuariosFake);
    }
}
