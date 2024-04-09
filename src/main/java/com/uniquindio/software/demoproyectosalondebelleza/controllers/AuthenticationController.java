package com.uniquindio.software.demoproyectosalondebelleza.controllers;

import com.uniquindio.software.demoproyectosalondebelleza.dto.AuthenticationRequest;
import com.uniquindio.software.demoproyectosalondebelleza.dto.AuthenticationResponse;
import com.uniquindio.software.demoproyectosalondebelleza.entities.Cita;
import com.uniquindio.software.demoproyectosalondebelleza.entities.Usuario;
import com.uniquindio.software.demoproyectosalondebelleza.respositories.CitaRepository;
import com.uniquindio.software.demoproyectosalondebelleza.services.implementations.AuthenticationServiceImpl;
import com.uniquindio.software.demoproyectosalondebelleza.services.implementations.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @Autowired
    private UsuarioServiceImpl usuarioDao;

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
}
