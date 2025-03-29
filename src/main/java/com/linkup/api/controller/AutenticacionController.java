package com.linkup.api.controller;

import com.linkup.api.dto.LoginRequest;
import com.linkup.api.dto.RegistroRequest;
import com.linkup.api.model.Emprendedor;
import com.linkup.api.model.Mentor;
import com.linkup.api.service.AutenticacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AutenticacionController {
    @Autowired
    private AutenticacionService autenticacionService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        String principalId = loginRequest.principalId();
        String tipoUsuario = loginRequest.tipoUsuario();

        Object usuario = autenticacionService.iniciarSesion(principalId, tipoUsuario);

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/registrar/mentor")
    public ResponseEntity<Mentor> crearCuentaMentor(@RequestBody RegistroRequest crearCuentaRequest) {
        String principalId = crearCuentaRequest.principalId();
        String nombre = crearCuentaRequest.nombre();
        String areaEspecializacion = crearCuentaRequest.areaEspecializacion();
        String tiempoRespuesta = crearCuentaRequest.tiempoRespuesta();
        String sitioWeb = crearCuentaRequest.sitioWeb();
        String sobreMi = crearCuentaRequest.sobreMi();
        Integer edad = crearCuentaRequest.edad();

        Mentor mentor = autenticacionService.crearCuentaMentor(principalId, nombre, areaEspecializacion,
                tiempoRespuesta, sitioWeb, sobreMi, edad);

        return ResponseEntity.ok(mentor);
    }

    @PostMapping("/registrar/emprendedor")
    public ResponseEntity<Emprendedor> crearCuentaEmprendedor(@RequestBody RegistroRequest crearCuentaRequest) {
        String principalId = crearCuentaRequest.principalId();
        String nombreCompleto = crearCuentaRequest.nombreCompleto();
        String correo = crearCuentaRequest.correo();
        String sobreMi = crearCuentaRequest.sobreMi();
        String ubicacion = crearCuentaRequest.ubicacion();
        String categoria = crearCuentaRequest.categoria();
        String sitioWeb = crearCuentaRequest.sitioWeb();
        Integer edad = crearCuentaRequest.edad();

        Emprendedor emprendedor = autenticacionService.crearCuentaEmprendedor(principalId, nombreCompleto, correo,
                sobreMi, ubicacion, categoria, sitioWeb, edad);

        return ResponseEntity.ok(emprendedor);
    }
}
