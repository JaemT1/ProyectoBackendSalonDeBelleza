package com.uniquindio.software.demoproyectosalondebelleza.controllers;

import com.uniquindio.software.demoproyectosalondebelleza.dto.AuthenticationRequest;
import com.uniquindio.software.demoproyectosalondebelleza.dto.AuthenticationResponse;
import com.uniquindio.software.demoproyectosalondebelleza.entities.Cita;
import com.uniquindio.software.demoproyectosalondebelleza.entities.Usuario;
import com.uniquindio.software.demoproyectosalondebelleza.respositories.CitaRepository;
import com.uniquindio.software.demoproyectosalondebelleza.services.implementations.AuthenticationServiceImpl;
import com.uniquindio.software.demoproyectosalondebelleza.services.implementations.UsuarioServiceImpl;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private UsuarioServiceImpl usuarioDao;

    public HashMap<String, Integer> codigosGenerados = new HashMap<String, Integer>();

    @PreAuthorize("permitAll")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody @Valid AuthenticationRequest authRequest){
        AuthenticationResponse jwtDto = authenticationService.login(authRequest);
        return ResponseEntity.ok(jwtDto);
    }

    @PreAuthorize("permitAll")
    @PostMapping("/signup")
    public ResponseEntity<Usuario> signUp(@RequestBody @Valid Usuario usuario){
        return ResponseEntity.ok(usuarioDao.guardar(usuario));
    }

    @PostMapping ("/verificationCodePasswordRecovery")
    public ResponseEntity<String> obtenerCorreo(@RequestBody Map<String, Object> passowrdRecoveryData) throws MessagingException {
        String correo = (String) passowrdRecoveryData.get("correo");
        String correoBD = usuarioDao.obtenerCorreoUsuario(correo);

        if (correoBD != null) {
            int codigoVerificacion = usuarioDao.generarCodigoVerificacion(6);
            this.codigosGenerados.put(correo, codigoVerificacion);
            System.out.println(codigosGenerados);
            usuarioDao.enviarCorreoCodigoVerificacion(correo, codigoVerificacion);
            return ResponseEntity.ok("Código Enviado");
        } else {
            // Correo incorrecto, no existe el usuario
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No existe el usuario");
        }
    }
}
