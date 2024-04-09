package com.uniquindio.software.demoproyectosalondebelleza.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.uniquindio.software.demoproyectosalondebelleza.utils.EstadoCita;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="citas")
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cita;
    //private int id_cliente;
    private Date fecha_cita;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Usuario cliente;
    @ManyToOne
    @JoinColumn(name = "id_empleado")
    private Usuario empleado;
    //private int id_empleado;
    @Enumerated(EnumType.STRING)
    private EstadoCita estado_cita;

    @ManyToMany(mappedBy = "citas")
    private List<Servicio> servicios;

}
