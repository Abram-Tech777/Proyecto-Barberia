package pe.Barberia.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.Barberia.enums.EstadoCita;
import pe.Barberia.enums.TipoPago;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barbero_id", nullable = false)
    @JsonBackReference
    private Barbero barbero;

    @Column(nullable = false)
    private String nombreCliente;

    @Column(nullable = false)
    private String celularCliente;

    @Column(nullable = false)
    private LocalDateTime horaInicio;

    @Column(nullable = false)
    private LocalDateTime horaFin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCita estado;

    private String codigoPago;

    @Enumerated(EnumType.STRING)
    private TipoPago tipoPago;

    private Double montoTotal;

    private Double montoAbonado;

    private Double saldoPendiente;

    @Column(nullable = false)
    private boolean selloAplicado;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;
}
