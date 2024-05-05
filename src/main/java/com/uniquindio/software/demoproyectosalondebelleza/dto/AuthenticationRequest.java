package com.uniquindio.software.demoproyectosalondebelleza.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthenticationRequest {
    private String correo;
    private String contrasena;
}
