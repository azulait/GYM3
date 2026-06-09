package gym.ms_nutricion.Repository;

import gym.ms_nutricion.Model.PlanNutricional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanNutricionalRepository extends JpaRepository<PlanNutricional, Integer> {
}