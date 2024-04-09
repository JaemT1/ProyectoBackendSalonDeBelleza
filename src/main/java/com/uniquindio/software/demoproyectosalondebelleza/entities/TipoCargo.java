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
@Table(name="tipos_cargos_empleados")
public class TipoCargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_tipo;
    @Column(length = 40)
    private String descripcion;

    @ManyToMany
    @JoinTable(name = "cargos_empleados",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_tipo"))
    private List<Usuario> usuarios;
}
