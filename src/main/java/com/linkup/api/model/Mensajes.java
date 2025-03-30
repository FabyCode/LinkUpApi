package com.linkup.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "mensajes")
public class Mensajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contenido", nullable = false, length = 256)
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "emprendedor_id", nullable = false)
    private Emprendedor emprendedor;

    @ManyToOne
    @JoinColumn(name = "mentor_id", nullable = false)
    private Mentor mentor;

    @Column(name = "fecha_envio", nullable = false)
    private LocalDateTime fechaEnvio;
}
