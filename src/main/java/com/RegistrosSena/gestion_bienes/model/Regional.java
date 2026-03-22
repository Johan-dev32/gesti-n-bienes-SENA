package com.RegistrosSena.gestion_bienes.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "regionales")
public class Regional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private String nombre;

    @OneToMany(mappedBy = "regional")
    private List<CentroFormacion> centros;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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