package com.misa.libreria.repository;

import com.misa.libreria.model.Autor;
import com.misa.libreria.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    Optional<Libro> libroPorTitulo(String titulo);
    @Query("SELECT a FROM Libro l JOIN l.autor a")
    List<Autor> autores();
    @Query("SELECT a FROM Libro l JOIN l.autor a WHERE a.fechaDeNacimiento <= :fecha")
    List<Autor> autoresHastaFecha(Integer fecha);
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> libroPorIdioma(List<String> idioma);
}


