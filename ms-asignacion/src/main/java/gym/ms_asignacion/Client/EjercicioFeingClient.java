package gym.ms_asignacion.Client;

import gym.ms_asignacion.Model.DTO.EjercicioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-ejercicios", path = "/api/v1/ejercicios")
public interface EjercicioFeingClient {
    @GetMapping("/{id}")
    EjercicioDTO obtenerEjercicioPorId(@PathVariable("id") int id);
}
