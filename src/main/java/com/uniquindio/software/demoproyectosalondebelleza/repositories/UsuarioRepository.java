package com.uniquindio.software.demoproyectosalondebelleza.repositories;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    @Query("SELECT u FROM Usuario u WHERE u.correo = ?1")
    Optional<Usuario> obtenerUsuarioPorCorreo(String correo);

    Optional<Usuario> findByCorreo(String correo);

    @Query("SELECT u.correo FROM Usuario u where u.correo = ?1")
    String obtenerCorreoUsuario(String correo);

    @Modifying
    @Query("UPDATE Usuario u SET u.contrasena = ?1 WHERE u.correo = ?2")
    void cambiarContrasena(String contrasenaNueva, String correo);
}
