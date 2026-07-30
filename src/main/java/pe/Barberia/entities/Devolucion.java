package pe.Barberia.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import pe.Barberia.enums.EstadoDevolucion;

import java.time.LocalDateTime;

@Entity
@Table(name = "devoluciones")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Devolucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cita_id", nullable = false, unique = true)
    private Cita cita;

    @Column(nullable = false)
    private Double montoReembolso;

    @Column(nullable = false)
    private String celularCliente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoDevolucion estado;

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;
}
