package gym.ms_pago;

import gym.ms_pago.Model.Pago;
import gym.ms_pago.Repository.PagoRepository;
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
public class DataLoader implements CommandLineRunner {

    @Autowired
    private PagoRepository repository;

    @Override
    public void run(String... args) throws Exception {
        List<Pago> pagosFake = new ArrayList<>();
        Random random = new Random();
        String[] metodos = {"Tarjeta de Crédito", "Transferencia", "Efectivo", "Tarjeta de Débito"};

        for (int i = 1; i <= 5; i++) {
            Pago pago = new Pago();
            pago.setUsuarioId(i);
            pago.setMonto(20000.0 + random.nextInt(30000));
            pago.setMetodoPago(metodos[random.nextInt(metodos.length)]);
            pago.setFechaPago(LocalDate.now().minusDays(random.nextInt(30)));

            pagosFake.add(pago);
        }

        repository.saveAll(pagosFake);
    }
}