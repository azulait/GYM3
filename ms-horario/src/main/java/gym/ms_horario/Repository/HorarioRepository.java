package gym.ms_horario.Repository;
import gym.ms_horario.Model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepository extends JpaRepository<Horario, Integer> { }