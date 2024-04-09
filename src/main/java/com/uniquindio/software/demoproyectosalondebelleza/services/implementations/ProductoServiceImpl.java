package com.uniquindio.software.demoproyectosalondebelleza.services.implementations;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Producto;
import com.uniquindio.software.demoproyectosalondebelleza.respositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.uniquindio.software.demoproyectosalondebelleza.services.ProductoService;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    @Transactional
    public Producto guardar(Producto producto) {
        return productoDao.save(producto);
    }



}
