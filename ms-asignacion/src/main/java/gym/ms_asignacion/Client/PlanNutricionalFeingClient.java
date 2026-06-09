package gym.ms_asignacion.Client;

import gym.ms_asignacion.Model.DTO.PlanNutricionalDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-nutricion", url = "http://localhost:8083/api/v1/nutricion")

public interface PlanNutricionalFeingClient {
    @GetMapping("/{id}")
    PlanNutricionalDTO obtenerPlanPorId(@PathVariable("id") int id);
}
