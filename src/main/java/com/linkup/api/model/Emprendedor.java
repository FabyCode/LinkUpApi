package com.linkup.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Emprendedor implements Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String principalId;

    private String nombreCompleto;
    private String correo;
    private String sobreMi;
    private String ubicacion;
    private String categoria;
    private String sitioWeb;
    private Integer edad;

    @OneToMany(mappedBy = "emprendedor")
    private List<Mensajes> mensajes;

    @Override
    public String getPrincipalId() {
        return principalId;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public Long getId() {
        return id;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setSobreMi(String sobreMi) {
        this.sobreMi = sobreMi;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setPrincipalId(String principalId) {
        this.principalId = principalId;
    }
}
