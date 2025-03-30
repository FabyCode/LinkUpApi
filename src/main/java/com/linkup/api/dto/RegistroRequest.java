package com.linkup.api.dto;

public record RegistroRequest(
        String principalId,
        String nombre,
        String areaEspecializacion,
        String tiempoRespuesta,
        String sitioWeb,
        String sobreMi,
        Integer edad,
        String nombreCompleto,
        String correo,
        String ubicacion,
        String categoria,
        String imagenPerfilUrl
) { }
