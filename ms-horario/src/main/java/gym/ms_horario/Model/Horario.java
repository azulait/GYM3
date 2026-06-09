package gym.ms_horario.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombreClase;
    private String diaSemana;
    private LocalTime horaInicio;

    @JsonManagedReference
    @OneToOne(mappedBy = "horario", cascade = CascadeType.ALL)
    private DetalleHorario detalleHorario;

    @ElementCollection
    @CollectionTable(
            name = "horario_usuario_ids",
            joinColumns = @JoinColumn(name = "horario_id")
    )
    @Column(name = "usuario_id")
    private List<Integer> usuariosIds;

    @ElementCollection
    @CollectionTable(
            name = "horario_entrenador_ids",
            joinColumns = @JoinColumn(name = "horario_id")
    )
    @Column(name = "entrenador_id")
    private List<Integer> entrenadoresIds;
}