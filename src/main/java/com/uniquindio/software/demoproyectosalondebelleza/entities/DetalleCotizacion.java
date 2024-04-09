package com.uniquindio.software.demoproyectosalondebelleza.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="detalles_cotizaciones")
public class DetalleCotizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle;
    @ManyToOne
    @JoinColumn(name = "id_cotizacion")
    private Cotizacion id_cotizacion;
    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio id_servicio;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto id_producto;
    private int cantidad;
    private double precio_unitario;

}
