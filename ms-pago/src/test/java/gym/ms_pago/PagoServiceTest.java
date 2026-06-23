package gym.ms_pago;

import gym.ms_pago.Model.DTO.UsuarioDTO;
import gym.ms_pago.Model.Pago;
import gym.ms_pago.Repository.PagoRepository;
import gym.ms_pago.Service.PagoService;
import gym.ms_pago.Client.UsuarioFeignClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PagoServiceTest {

    @InjectMocks
    private PagoService pagoService;

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private UsuarioFeignClient usuarioClient;

    @Test
    public void testObtenerReciboCompleto_Exitoso() {
        Pago mockPago = new Pago();
        mockPago.setId(1);
        mockPago.setUsuarioId(10);
        mockPago.setMonto(30000.0);

        when(pagoRepository.findById(1)).thenReturn(Optional.of(mockPago));

        when(usuarioClient.obtenerUsuarioPorId(10)).thenReturn(new UsuarioDTO());

        Map<String, Object> recibo = pagoService.obtenerReciboCompleto(1);

        assertFalse(recibo.isEmpty());
        assertTrue(recibo.containsKey("detallePago"));
        assertTrue(recibo.containsKey("cliente"));
    }

    @Test
    public void testGuardar_Exitoso() {
        Pago p = new Pago();
        p.setMonto(15000.0);

        when(pagoRepository.save(any(Pago.class))).thenReturn(p);

        Pago guardado = pagoService.guardar(p);
        assertNotNull(guardado);
        assertEquals(15000.0, guardado.getMonto());
    }

    @Test
    public void testEliminar() {
        int id = 1;
        when(pagoRepository.existsById(id)).thenReturn(true);
        pagoService.eliminar(id);
        verify(pagoRepository, times(1)).deleteById(id);
    }
}