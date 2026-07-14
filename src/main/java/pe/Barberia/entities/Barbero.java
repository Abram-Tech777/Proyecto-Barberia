package pe.Barberia.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "barberos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Barbero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreCompleto;

    private Double porcentajeComision;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    @JsonManagedReference
    private Usuario usuario;

    @Column(nullable = false)
    private boolean activo;
}
