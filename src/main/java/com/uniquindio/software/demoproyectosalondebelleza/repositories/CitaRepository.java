package com.uniquindio.software.demoproyectosalondebelleza.repositories;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Cita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Integer> {
}
