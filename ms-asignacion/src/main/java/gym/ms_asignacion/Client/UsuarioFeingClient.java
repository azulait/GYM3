package gym.ms_asignacion.Client;

import gym.ms_asignacion.Model.DTO.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-usuario", path = "/api/v1/usuarios")
public interface UsuarioFeingClient {

    @GetMapping("/{id}")
    UsuarioDTO obtenerUsuarioPorId(@PathVariable("id") int id);
}