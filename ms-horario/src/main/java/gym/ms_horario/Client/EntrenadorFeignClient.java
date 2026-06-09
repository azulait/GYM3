package gym.ms_horario.Client;

import gym.ms_horario.Model.DTO.EntrenadorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-entrenador", path = "/api/v1/entrenadores")
public interface EntrenadorFeignClient {

    @GetMapping("/{id}")
    EntrenadorDTO obtenerEntrenadorPorId(@PathVariable("id") int id);
}