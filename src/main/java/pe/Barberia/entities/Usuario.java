package pe.Barberia.entities;

import jakarta.persistence.*;
import lombok.Data;
import pe.Barberia.enums.TipoRegistro;
import pe.Barberia.enums.Rol;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
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
    private TipoRegistro tipoRegistro;

    @Column(unique = true)
    private String idGoogle;

    @Column(nullable = false)
    private boolean recibirPromociones;

    @Column(nullable = false)
    private boolean activo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DireccionEnvio> direcciones = new ArrayList<>();
}
