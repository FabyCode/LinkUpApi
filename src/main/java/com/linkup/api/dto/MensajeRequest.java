package com.linkup.api.dto;

public class MensajeRequest {
    private Long emprendedorId;
    private Long mentorId;
    private String contenido;

    public Long getEmprendedorId() {
        return emprendedorId;
    }

    public void setEmprendedorId(Long emprendedorId) {
        this.emprendedorId = emprendedorId;
    }

    public Long getMentorId() {
        return mentorId;
    }

    public void setMentorId(Long mentorId) {
        this.mentorId = mentorId;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
