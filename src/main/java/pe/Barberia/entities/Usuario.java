package pe.Barberia.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.Barberia.enums.ProveedorAutenticacion;
import pe.Barberia.enums.Rol;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombreUsuario;

    private String contrasenia;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String telefono;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProveedorAutenticacion proveedorAutenticacion;

    @Column(unique = true)
    private String proveedorId;

    @Column(nullable = false)
    private boolean preferenciasMarketing;

    @Column(nullable = false)
    private boolean activo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DireccionEnvio> direcciones = new ArrayList<>();
}
