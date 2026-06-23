package gym.ms_pago.Controller;

import gym.ms_pago.Model.Pago;
import gym.ms_pago.Service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Gestión de Pagos", description = "Administra los pagos de membresías y recibos de los usuarios")
public class PagoController {

    @Autowired
    private PagoService service;

    @GetMapping
    @Operation(summary = "Listar todos los pagos", description = "Retorna el historial completo de pagos registrados.")
    public ResponseEntity<List<Pago>> listarTodos() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    @Operation(summary = "Registrar un pago", description = "Crea un nuevo registro de pago en el sistema.")
    public ResponseEntity<Pago> registrarPago(@RequestBody Pago pago) {
        Pago nuevoPago = service.guardar(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener recibo completo", description = "Trae el detalle del pago y muestra la información del cliente.")
    public ResponseEntity<Map<String, Object>> verRecibo(@PathVariable int id) {
        Map<String, Object> respuesta = service.obtenerReciboCompleto(id);

        if (respuesta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar pago", description = "Borra físicamente un pago de la base de datos.")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}