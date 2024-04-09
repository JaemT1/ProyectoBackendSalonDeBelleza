package com.uniquindio.software.demoproyectosalondebelleza.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.lang.model.element.ModuleElement;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name="detalles_ventas")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle_venta;
    @ManyToOne
    @JoinColumn(name = "id_factura")
    private FacturaVenta id_factura;
    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto id_producto;
    @ManyToOne
    @JoinColumn(name = "id_servicio")
    private Servicio id_servicio;
    private int cantidad;
    private double precio_unitario;
    private double subtotal;
}
