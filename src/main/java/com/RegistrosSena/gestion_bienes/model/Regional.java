package com.RegistrosSena.gestion_bienes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name = "regionales")
public class Regional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código es obligatorio")
    @Column(unique = true, nullable = false)
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(unique = true, nullable = false)
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "regional")
    private List<CentroFormacion> centros;

    // GETTERS Y SETTERS

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CentroFormacion> getCentros() {
        return centros;
    }

    public void setCentros(List<CentroFormacion> centros) {
        this.centros = centros;
    }
}