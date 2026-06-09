package gym.ms_entrenador.repository;


import gym.ms_entrenador.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador,Integer> {
}
