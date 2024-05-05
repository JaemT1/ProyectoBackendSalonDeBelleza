package com.uniquindio.software.demoproyectosalondebelleza.repositories;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Usuario;
import com.uniquindio.software.demoproyectosalondebelleza.services.implementations.UsuarioServiceImpl;
import com.uniquindio.software.demoproyectosalondebelleza.utils.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class correoUnicoTest {

    @Autowired
    private UsuarioRepository usuarioDao;

    @Autowired
    private UsuarioServiceImpl usuarioService;
    @Test
    public void testCorreoUnicoUsuario() throws Exception {
        Usuario usuario = Usuario.builder()
                .id_usuario(5)
                .nombre("Alejo")
                .contrasena("@AlejoLope")
                .telefono("3214867021")
                .correo("nicolasrrk04@gmail.com")
                .apellido("Lopez")
                .link_foto("prueba")
                .role(Role.CUSTOMER)
                .build();
        if(!existeCorreo(usuario.getCorreo())) throw new Exception("No se pudo registrar el usuario: el correo ya esta registrado");
        if(!existeId(usuario.getId_usuario())) throw new Exception("No se pudo registrar el usuario: el id del usuario ya existe");
        usuarioService.guardar(usuario);
    }

    // Si existe, retorna False, si no existe, retorna True
    public boolean existeCorreo(String correo){
        Optional<Usuario> usuarioEncontrado = usuarioDao.obtenerUsuarioPorCorreo(correo);
        return usuarioEncontrado.isEmpty();
    }

    public boolean existeId(int id_usuario){
        Optional<Usuario> usuarioEncontrado = usuarioDao.findById(id_usuario);
        return usuarioEncontrado.isEmpty();
    }

}
