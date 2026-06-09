package gym.ms_horario.Client;

import gym.ms_horario.Model.DTO.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-usuario", url = "http://localhost:8084/api/v1/usuarios")
public interface UsuarioFeignClient {

    @GetMapping("/{id}")
    UsuarioDTO obtenerUsuarioPorId(@PathVariable("id") int id);
}