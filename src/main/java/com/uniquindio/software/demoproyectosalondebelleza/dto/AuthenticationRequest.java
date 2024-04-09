package com.uniquindio.software.demoproyectosalondebelleza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationRequest {
    private String correo;
    private String contrasena;
}
