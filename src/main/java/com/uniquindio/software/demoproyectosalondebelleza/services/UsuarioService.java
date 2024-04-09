package com.uniquindio.software.demoproyectosalondebelleza.services;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorCorreo(String correo);
}
