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
@Table(name="facturas_ventas")
public class FacturaVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_factura;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Usuario id_cliente;
    private double total_factura;
    @Column(length = 500)
    private String consideraciones_adicionales;
    private double descuentos;
    private double impuestos;
}
