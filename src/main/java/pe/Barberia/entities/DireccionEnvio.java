package pe.Barberia.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "direcciones_envio")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class DireccionEnvio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonIgnoreProperties("direcciones")
    private Usuario usuario;

    @Column(nullable = false)
    private String nombreDireccion;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String distrito;

    @Column(nullable = false)
    private String provincia;

    @Column(nullable = false)
    private String departamento;

    private String codigoPostal;

    @Column(nullable = false)
    private String telefonoContacto;
}
