package pe.Barberia.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.Barberia.enums.EstadoPedido;
import pe.Barberia.enums.MedioPago;
import pe.Barberia.enums.OrigenOrden;
import pe.Barberia.enums.TipoDespacho;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comprador_id")
    private Usuario comprador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cita_id")
    private Cita citaAsociada;

    @Column(nullable = false)
    private LocalDateTime fechaTransaccion;

    @Column(nullable = false)
    private Double montoTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedioPago medioPago;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoDespacho tipoDespacho;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrigenOrden origenOrden;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direccion_envio_id")
    private DireccionEnvio direccionEnvio;

    private String coordenadasEnvio;

    private String referenciaDireccion;

    private Double costoEnvio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estadoPedido;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleVenta> detalles;
}
