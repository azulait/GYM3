package gym.ms_horario.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonBackReference;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "detalle_horario")
public class DetalleHorario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int capacidadMaxima;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "horario_id")
    private Horario horario;
}