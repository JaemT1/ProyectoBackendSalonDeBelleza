package com.uniquindio.software.demoproyectosalondebelleza.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="servicios")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_servicio;
    @Column(length = 100)
    private String nombre;
    @Column(length = 100)
    private String descripcion;
    private int duracion_estimada;
    private double precio;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoriaServicio_id_categoria")
    private CategoriaServicio categoriaServicio;

    @ManyToMany
    @JoinTable(name = "servicios_cita",
            joinColumns = @JoinColumn(name = "id_cita"),
            inverseJoinColumns = @JoinColumn(name = "id_servicio"))
    private List<Cita> citas;
}
