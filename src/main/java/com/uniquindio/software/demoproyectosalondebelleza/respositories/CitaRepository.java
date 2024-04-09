package com.uniquindio.software.demoproyectosalondebelleza.respositories;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Cita;
import com.uniquindio.software.demoproyectosalondebelleza.entities.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Integer> {
}
