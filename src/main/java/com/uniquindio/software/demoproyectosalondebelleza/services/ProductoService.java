package com.uniquindio.software.demoproyectosalondebelleza.services;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listarProductos();
    Producto guardar(Producto producto);
}
