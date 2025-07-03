package com.losung.projectmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "bitacora")
@AllArgsConstructor
@NoArgsConstructor
public class Bitacora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BitAcciones accion;

    @Column(nullable = false)
    private String usuario;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(length = 1000)
    private String descripcion;

    public Bitacora(BitAcciones accion, String usuario, LocalDateTime fecha, String descripcion) {
        this.accion = accion;
        this.usuario = usuario;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }
}
