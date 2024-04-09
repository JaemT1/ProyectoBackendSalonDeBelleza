package com.uniquindio.software.demoproyectosalondebelleza.services.implementations;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Cita;
import com.uniquindio.software.demoproyectosalondebelleza.respositories.CitaRepository;
import com.uniquindio.software.demoproyectosalondebelleza.services.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaDao;

    @Override
    public List<Cita> listarCitas() {
        return (List<Cita>) citaDao.findAll();
    }

    @Override
    public Cita guardar(Cita cita) {
        return citaDao.save(cita);
    }
}
