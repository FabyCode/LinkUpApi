package com.linkup.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mentor implements Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String principalId;

    private String nombre;
    private String areaEspecializacion;
    private String tiempoRespuesta;
    private String sitioWeb;
    private String sobreMi;
    //private String cv = null;
    private Integer edad;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aptitud> loQueOfrece;

    @OneToMany(mappedBy = "mentor")
    private List<Mensajes> mensajes;

    @Override
    public String getPrincipalId() {
        return principalId;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public void setTiempoRespuesta(String tiempoRespuesta) {
        this.tiempoRespuesta = tiempoRespuesta;
    }

    public void setAreaEspecializacion(String areaEspecializacion) {
        this.areaEspecializacion = areaEspecializacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }
}
