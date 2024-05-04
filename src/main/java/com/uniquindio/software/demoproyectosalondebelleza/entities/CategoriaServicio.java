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
@Table(name="categorias_servicios")
public class CategoriaServicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_categoria;
    @Column(length = 100)
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "categoriaServicio", cascade = CascadeType.ALL)
    private List<Servicio> servicios;


}
