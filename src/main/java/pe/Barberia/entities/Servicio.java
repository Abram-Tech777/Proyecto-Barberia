package pe.Barberia.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "servicios")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    @Column(nullable = false)
    private Integer duracionMinutos;

    @Column(nullable = false)
    private Double precioBase;

    @ManyToMany(mappedBy = "servicios")
    private List<Promocion> promociones;
}
