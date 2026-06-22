package gym.ms_asignacion.Model;

import gym.ms_asignacion.Model.DTO.EjercicioDTO;
import gym.ms_asignacion.Model.DTO.PlanNutricionalDTO;
import gym.ms_asignacion.Model.DTO.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "asignacion")
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int usuarioId;
    private int planNutricionalId;
    private int ejercicioId;

}