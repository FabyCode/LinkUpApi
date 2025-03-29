package com.linkup.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Aptitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
}