package com.uniquindio.software.demoproyectosalondebelleza.services.implementations;

import com.uniquindio.software.demoproyectosalondebelleza.dto.AuthenticationRequest;
import com.uniquindio.software.demoproyectosalondebelleza.dto.AuthenticationResponse;
import com.uniquindio.software.demoproyectosalondebelleza.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioServiceImpl usuarioRepository;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authRequest) {

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                authRequest.getCorreo(), authRequest.getContrasena()
        );

        authenticationManager.authenticate(authToken);

        Usuario usuario = usuarioRepository.buscarPorCorreo(authRequest.getCorreo()).get();

        String jwt = jwtService.generateToken(usuario, generateExtraClaims(usuario));

        return new AuthenticationResponse(jwt);
    }

    private Map<String, Object> generateExtraClaims(Usuario usuario) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", usuario.getNombre());
        extraClaims.put("role", usuario.getRole().name());
        extraClaims.put("permissions", usuario.getAuthorities());
        return extraClaims;
    }
}
