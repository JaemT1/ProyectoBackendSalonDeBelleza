package com.uniquindio.software.demoproyectosalondebelleza.entities;

import com.uniquindio.software.demoproyectosalondebelleza.utils.EstadoCotizacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="cotizaciones")
public class Cotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cotizacion;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Usuario id_cliente;
    private Date fecha;
    private int validez_dias;
    private double total;
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EstadoCotizacion estado_cotizacion;
    @Column(length = 400)
    private String notas;
}
