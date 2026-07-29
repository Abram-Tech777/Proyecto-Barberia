package pe.Barberia.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tarjetas_lealtad")
@Data
public class TarjetaLealtad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String celularCliente;

    @Column(nullable = false)
    private Integer cantidadSellos;
}
