package com.uniquindio.software.demoproyectosalondebelleza.repositories;

import com.uniquindio.software.demoproyectosalondebelleza.dto.AuthenticationRequest;
import com.uniquindio.software.demoproyectosalondebelleza.services.implementations.AuthenticationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
public class loginUsuarioTest {

    @Autowired
    AuthenticationServiceImpl authenticationService;

    @Test
    public void testLoginUsuario() {
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder()
                .correo("nicolasrr2k04@gmail.com")
                .contrasena("@Nietadan2")
                .build();

        authenticationService.login(authenticationRequest);
    }
}
