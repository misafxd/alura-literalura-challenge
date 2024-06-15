package com.misa.libreria.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private List<String> idioma;
    private Integer descargas;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "autor_id")
    private Autor autor;


    public Libro(){}

    public Libro(DatosLibro datosLibro) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idiomas();
        this.descargas = datosLibro.descargas();
        this.autor = new Autor(datosLibro.autores().get(0));
    }

    @Override
    public String toString() {
        return String.format("Titulo: %s%nAutor: %s%nIdioma: %s%nNumero de descargas: %s%n", titulo, autor, idioma, descargas);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(DatosAutor autor) {
        this.autor = new Autor(autor);
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }


}
