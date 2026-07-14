package pe.Barberia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tarjetas_lealtad")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TarjetaLealtad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String celularCliente;

    @Column(nullable = false)
    private Integer cantidadSellos;
}
