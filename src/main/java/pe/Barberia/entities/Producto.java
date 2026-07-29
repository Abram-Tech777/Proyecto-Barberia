package pe.Barberia.entities;

import jakarta.persistence.*;
import lombok.Data;
import pe.Barberia.enums.CategoriaProducto;

import java.util.List;

@Entity
@Table(name = "productos")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String descripcionCorta;

    @Column(columnDefinition = "TEXT")
    private String descripcionLarga;

    private String imagenUrl;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stock;

    private Integer stockMinimo;

    @Enumerated(EnumType.STRING)
    private CategoriaProducto categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Resenia> resenias;

    @ManyToMany(mappedBy = "productos")
    private List<Promocion> promociones;
}
