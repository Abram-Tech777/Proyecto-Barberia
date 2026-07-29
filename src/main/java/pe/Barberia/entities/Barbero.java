package pe.Barberia.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "barberos")
@Data
public class Barbero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombreCompleto;

    private Double porcentajeComision;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false)
    private boolean activo;
}
