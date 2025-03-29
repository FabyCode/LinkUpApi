package com.linkup.api.service;

import com.linkup.api.model.Emprendedor;
import com.linkup.api.model.Mentor;
import com.linkup.api.repository.EmprendedorRepository;
import com.linkup.api.repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacionService {
    @Autowired
    private EmprendedorRepository emprendedorRepository;

    @Autowired
    private MentorRepository mentorRepository;

    public Object iniciarSesion(String principalId, String tipoUsuario) {
        if ("Mentor".equals(tipoUsuario)) {
            return mentorRepository.findByPrincipalId(principalId);
        } else if ("Emprendedor".equals(tipoUsuario)) {
            return emprendedorRepository.findByPrincipalId(principalId);
        }
        return null;
    }

    public Mentor crearCuentaMentor(String principalId, String nombre, String areaEspecializacion,
                                    String tiempoRespuesta, String sitioWeb, String sobreMi, Integer edad) {
        Mentor mentor = new Mentor();
        mentor.setPrincipalId(principalId);
        mentor.setNombre(nombre);
        mentor.setAreaEspecializacion(areaEspecializacion);
        mentor.setTiempoRespuesta(tiempoRespuesta);
        mentor.setSitioWeb(sitioWeb);
        mentor.setSobreMi(sobreMi);
        mentor.setEdad(edad);

        return mentorRepository.save(mentor);
    }

    public Emprendedor crearCuentaEmprendedor(String principalId, String nombreCompleto, String correo,
                                              String sobreMi, String ubicacion, String categoria, String sitioWeb, Integer edad) {
        Emprendedor emprendedor = new Emprendedor();
        emprendedor.setPrincipalId(principalId);
        emprendedor.setNombreCompleto(nombreCompleto);
        emprendedor.setCorreo(correo);
        emprendedor.setSobreMi(sobreMi);
        emprendedor.setUbicacion(ubicacion);
        emprendedor.setCategoria(categoria);
        emprendedor.setSitioWeb(sitioWeb);
        emprendedor.setEdad(edad);

        return emprendedorRepository.save(emprendedor);
    }
}
