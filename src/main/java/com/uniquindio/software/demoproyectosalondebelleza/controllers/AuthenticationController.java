package com.uniquindio.software.demoproyectosalondebelleza.controllers;

import com.uniquindio.software.demoproyectosalondebelleza.dto.AuthenticationRequest;
import com.uniquindio.software.demoproyectosalondebelleza.dto.AuthenticationResponse;
import com.uniquindio.software.demoproyectosalondebelleza.entities.Usuario;
import com.uniquindio.software.demoproyectosalondebelleza.services.implementations.AuthenticationServiceImpl;
import com.uniquindio.software.demoproyectosalondebelleza.services.implementations.UsuarioServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private UsuarioServiceImpl usuarioDao;

    public HashMap<String, String> codigosGenerados = new HashMap<>();

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

    @PreAuthorize("permitAll")
    @PostMapping ("/verificationCodePasswordRecovery")
    public ResponseEntity<String> enviarCodigoVerificacion(@RequestBody Map<String, Object> passowrdRecoveryData) {
        String correo = (String) passowrdRecoveryData.get("correo");
        String correoBD = usuarioDao.obtenerCorreoUsuario(correo);

        if (correoBD != null) {
            boolean isCodeRepeted = true;
            String codigoVerificacion = "";
            while(isCodeRepeted){
                codigoVerificacion = usuarioDao.generarCodigoVerificacion(6);
                if (!codigosGenerados.containsValue(codigoVerificacion)){
                    isCodeRepeted = false;
                }
            }
            this.codigosGenerados.put(correo, codigoVerificacion);
            System.out.println(this.codigosGenerados);
            usuarioDao.enviarCorreoCodigoVerificacion(correo, codigoVerificacion);
            return ResponseEntity.ok("Código Enviado");
        } else {
            // Correo incorrecto, no existe el usuario
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No existe el usuario");
        }
    }

    @PostMapping ("/changePassword")
    public ResponseEntity<String> cambiarContrasena(@RequestBody Map<String, Object> newPasswordData) {
        String codigo =  (String)newPasswordData.get("verificationCode");
        String contrasenaNueva = (String) newPasswordData.get("passwordToSend");
        String correo = (String) newPasswordData.get("email");

        if (this.codigosGenerados.containsValue(codigo)) {
            usuarioDao.cambiarContrasena(contrasenaNueva, correo);
            //Quitar el codigo del hashmap
            this.codigosGenerados.remove(correo, codigo);
            return ResponseEntity.ok("Contraseña Cambiada con éxito");
        } else {
            // Código digitado incorrecto
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Código incorrecto");
        }
    }
}
