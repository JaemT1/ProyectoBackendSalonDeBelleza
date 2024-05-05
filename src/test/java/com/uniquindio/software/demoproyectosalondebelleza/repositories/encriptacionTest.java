package com.uniquindio.software.demoproyectosalondebelleza.repositories;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Usuario;
import com.uniquindio.software.demoproyectosalondebelleza.utils.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.function.Executable;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class encriptacionTest {

    @Autowired
    private PasswordEncoder passwordService;

    @Test
    public void testEncriptacion() throws Exception {
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
        Executable executable = () -> passwordService.upgradeEncoding(usuario.getContrasena());
        assertThrows(IllegalArgumentException.class, executable,"La contraseña está correctamente encriptada");
    }
}
