package gym.ms_pago.Service;

import gym.ms_pago.Model.Pago;
import gym.ms_pago.Repository.PagoRepository;
import gym.ms_pago.Client.UsuarioFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PagoService {

    @Autowired
    private PagoRepository repository;

    @Autowired
    private UsuarioFeignClient usuarioClient;

    public Pago guardar(Pago pago) {
        return repository.save(pago);
    }

    public Map<String, Object> obtenerReciboCompleto(int id) {
        Map<String, Object> recibo = new HashMap<>();

        Pago pago = repository.findById(id).orElse(null);
        if (pago == null) {
            return recibo;
        }

        recibo.put("detallePago", pago);
        try {
            recibo.put("cliente", usuarioClient.obtenerUsuarioPorId(pago.getUsuarioId()));
        } catch (Exception e) {
            recibo.put("cliente", "Información de cliente no disponible");
        }

        return recibo;
    }
}
