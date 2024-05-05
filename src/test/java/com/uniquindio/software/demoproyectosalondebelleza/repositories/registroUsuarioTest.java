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
public class registroUsuarioTest {

    @Autowired
    private UsuarioRepository usuarioDao;

   @Autowired
   UsuarioServiceImpl usuarioService;

   @Test
    public void testRegistroUsuario() throws Exception {
       Usuario usuario = Usuario.builder()
               .id_usuario(5)
               .nombre("Alejandro")
               .contrasena("$2a$10$l5whZD1IJ./o3fEwtkajK.giS.WI/UQmkiJuVw81OYuF1muQWZqWy")
               .telefono("3214867021")
               .correo("nicolasrrk04@gmail.com")
               .apellido("Lopez")
               .link_foto("prueba")
               .role(Role.CUSTOMER)
               .build();
       if(!existeId(usuario.getId_usuario())) throw new Exception("No se pudo registrar el usuario: el id del usuario ya existe");
       usuarioService.guardar(usuario);
   }

    public boolean existeId(int id_usuario){
        Optional<Usuario> usuarioEncontrado = usuarioDao.findById(id_usuario);
        return usuarioEncontrado.isEmpty();
    }

}
