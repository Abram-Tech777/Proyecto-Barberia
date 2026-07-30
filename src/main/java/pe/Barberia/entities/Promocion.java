package pe.Barberia.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "promociones")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Promocion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private Double porcentajeDescuento;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFin;

    @Column(nullable = false)
    private boolean activo;

    @ManyToMany
    @JoinTable(
            name = "promocion_servicio",
            joinColumns = @JoinColumn(name = "promocion_id"),
            inverseJoinColumns = @JoinColumn(name = "servicio_id")
    )
    @JsonIgnoreProperties("promociones")
    private List<Servicio> servicios;

    @ManyToMany
    @JoinTable(
            name = "promocion_producto",
            joinColumns = @JoinColumn(name = "promocion_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    @JsonIgnoreProperties({"promociones", "resenias"})
    private List<Producto> productos;
}
