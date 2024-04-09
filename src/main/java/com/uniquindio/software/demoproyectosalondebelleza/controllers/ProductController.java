package com.uniquindio.software.demoproyectosalondebelleza.controllers;

import com.uniquindio.software.demoproyectosalondebelleza.entities.Producto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.uniquindio.software.demoproyectosalondebelleza.services.implementations.ProductoServiceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductoServiceImpl productoService;

    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping
    public ResponseEntity<List<Producto>> findAll(){

        List<Producto> productos = productoService.listarProductos();

        if(productos != null && !productos.isEmpty()){
            return ResponseEntity.ok(productos);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('SAVE_ONE_PRODUCT')")
    @PostMapping
    public ResponseEntity<Producto> createOne(@RequestBody @Valid Producto producto){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                productoService.guardar(producto)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception exception, HttpServletRequest request){

        Map<String, String> apiError = new HashMap<>();
        apiError.put("message",exception.getLocalizedMessage());
        apiError.put("timestamp", new Date().toString());
        apiError.put("url", request.getRequestURL().toString());
        apiError.put("http-method", request.getMethod());

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if(exception instanceof AccessDeniedException){
            status = HttpStatus.FORBIDDEN;
        }

        return ResponseEntity.status(status).body(apiError);
    }



}
