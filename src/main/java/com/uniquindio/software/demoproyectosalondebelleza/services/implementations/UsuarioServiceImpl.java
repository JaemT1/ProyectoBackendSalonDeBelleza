package com.uniquindio.software.demoproyectosalondebelleza.services.implementations;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Usuario;
import com.uniquindio.software.demoproyectosalondebelleza.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniquindio.software.demoproyectosalondebelleza.respositories.UsuarioRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioDao;

    @Autowired
    private MailSenderImpl mailSenderService;

    @Override
    public List<Usuario> listarUsuarios() {
        return (List<Usuario>)usuarioDao.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioDao.obtenerUsuarioPorCorreo(correo);
    }

    public void enviarCorreoCodigoVerificacion(String correoDestino, String codigoVerificacion) {
        String contenido = "<html>" +
                "<body>" +
                "<p>Digite este código en el formulario para continuar con su cambio de contraseña:</p>" +
                "<p><strong>" + codigoVerificacion + "</strong></p>" +
                "<p>Respetado usuario, este correo ha sido generado por un sistema de envío; por favor <strong>NO</strong> responda al mismo ya que no podrá ser gestionado.</p>" +
                "</body>" +
                "</html>";
        mailSenderService.enviarEmail("Código de Verificación", contenido, correoDestino);
    }

    public String generarCodigoVerificacion(int cantDigitos) {
        if (cantDigitos < 1) {
            throw new IllegalArgumentException("El número de dígitos debe ser al menos 1.");
        }
        int min = (int) Math.pow(10, cantDigitos - 1); // Mínimo valor posible
        int max = (int) Math.pow(10, cantDigitos) - 1; // Máximo valor posible
        Random random = new Random();
        return String.valueOf(random.nextInt(max - min + 1) + min);
    }

    @Override
    @Transactional(readOnly = true)
    public String obtenerCorreoUsuario(String correo) {return usuarioDao.obtenerCorreoUsuario(correo);}

    @Override
    @Transactional
    public void cambiarContrasena(String contrasenaNueva, String correo) {
        usuarioDao.cambiarContrasena(contrasenaNueva, correo);
    }
}
