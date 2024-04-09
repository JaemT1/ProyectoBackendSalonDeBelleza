package com.uniquindio.software.demoproyectosalondebelleza.services;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Cita;

import java.util.List;

public interface CitaService {
    List<Cita> listarCitas();
    Cita guardar(Cita cita);
}
