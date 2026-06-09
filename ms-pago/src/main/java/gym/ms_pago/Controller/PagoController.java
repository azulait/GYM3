package gym.ms_pago.Controller;

import gym.ms_pago.Model.Pago;
import gym.ms_pago.Service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService service;

    @PostMapping
    public ResponseEntity<Pago> registrarPago(@RequestBody Pago pago) {
        Pago nuevoPago = service.guardar(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> verRecibo(@PathVariable int id) {
        Map<String, Object> respuesta = service.obtenerReciboCompleto(id);

        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(respuesta);
    }
}
