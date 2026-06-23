package gym.ms_pago.Service;

import gym.ms_pago.Model.Pago;
import gym.ms_pago.Repository.PagoRepository;
import gym.ms_pago.Client.UsuarioFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PagoService {

    @Autowired
    private PagoRepository repository;

    @Autowired
    private UsuarioFeignClient usuarioClient;

    public List<Pago> findAll() {
        log.debug("DEBUG: Consultando listado completo de pagos.");
        return repository.findAll();
    }

    public Pago guardar(Pago pago) {
        log.info("INFORMACION: Registrando nuevo pago por monto: {}", pago.getMonto());
        return repository.save(pago);
    }

    public void eliminar(int id) {
        if(!repository.existsById(id)){
            log.error("ERROR: Intento fallido de eliminar pago inexistente ID: {}", id);
            throw new RuntimeException("El pago no existe");
        }
        log.warn("ADVERTENCIA: Eliminando registro de pago ID: {}", id);
        repository.deleteById(id);
    }

    public Map<String, Object> obtenerReciboCompleto(int id) {
        log.info("INFORMACION: Generando recibo completo para el pago ID: {}", id);
        Map<String, Object> recibo = new HashMap<>();

        Pago pago = repository.findById(id).orElse(null);
        if (pago == null) {
            log.error("ERROR: Recibo no encontrado para el pago ID: {}", id);
            return recibo;
        }

        recibo.put("detallePago", pago);
        try {
            log.debug("DEBUG: Consumiendo microservicio ms-usuario vía Feign...");
            recibo.put("cliente", usuarioClient.obtenerUsuarioPorId(pago.getUsuarioId()));
        } catch (Exception e) {
            log.warn("ADVERTENCIA: ms-usuario no disponible para el ID: {}", pago.getUsuarioId());
            recibo.put("cliente", "Información de cliente no disponible (Servicio Caído)");
        }

        return recibo;
    }
}