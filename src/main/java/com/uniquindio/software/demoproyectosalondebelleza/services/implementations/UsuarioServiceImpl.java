package com.uniquindio.software.demoproyectosalondebelleza.services.implementations;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Usuario;
import com.uniquindio.software.demoproyectosalondebelleza.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniquindio.software.demoproyectosalondebelleza.respositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioDao;

    @Override
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>)usuarioDao.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioDao.obtenerUsuarioPorCorreo(correo);
    }
}
