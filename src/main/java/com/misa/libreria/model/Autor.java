package com.misa.libreria.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;


    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.autor();
        this.fechaDeNacimiento = datosAutor.nacimiento();
        this.fechaDeMuerte = datosAutor.muerte();
    }

    @Override
    public String toString() {
        return String.format("Autor: %s%nNacimiento: %s%nMuerte: %s%n", nombre, fechaDeNacimiento, fechaDeMuerte);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

}
