package com.uniquindio.software.demoproyectosalondebelleza.repositories;

import com.uniquindio.software.demoproyectosalondebelleza.services.implementations.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class recuperacionContrasenaTest {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Test
    public void testRecuperarContrasena() throws Exception {
        String correo = "nicolasrr2k04@gmail.com";
        //Verificar la existencia del correo
        assertNotNull(usuarioService.buscarPorCorreo(correo), "El usuario con el correo: " + correo + " no existe");
        //Generar el código
        String codigoEnviado = usuarioService.generarCodigoVerificacion(6);
        //Enviar el código
        usuarioService.enviarCorreoCodigoVerificacion(correo, codigoEnviado);
        //Comparar el código enviado con el código que ingresa el usuario
        String codigoIngresado = codigoEnviado;
        assertEquals(codigoEnviado, codigoIngresado, "El código ingresado no es correcto");
        //Contraseña nueva
        String contrasenaNueva = "$2a$10$l5whZD1IJ./o3fEwtkajK.giS.WI/UQmkiJuVw81OYuF1muQWZqWy";
        usuarioService.cambiarContrasena(contrasenaNueva, correo);
    }
}
