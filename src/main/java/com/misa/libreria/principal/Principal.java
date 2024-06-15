package com.misa.libreria.principal;

import com.misa.libreria.model.Autor;
import com.misa.libreria.model.Datos;
import com.misa.libreria.model.DatosLibro;
import com.misa.libreria.model.Libro;
import com.misa.libreria.repository.LibroRepository;
import com.misa.libreria.service.ConsumoAPI;
import com.misa.libreria.service.ConvierteDatos;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Principal {
    private ConvierteDatos conversor = new ConvierteDatos();
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private Scanner teclado = new Scanner(System.in);
    private LibroRepository repositorio;
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private List<Libro> libros;
    private List<Autor> autores;

    public Principal(LibroRepository repository){
        this.repositorio = repository;
    }

    public void muestraMenu(){
        var opcion = -1;
        while(opcion != 0){
            var menu = """
                     1.- Buscar libro
                     2.- Mostrar libros registrados
                     3.- Mostrar autores registrados
                     4.- Mostrar autores vivos en determinado año
                     5.- Mostrar libros por idioma
                     
                     0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion){
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    verLibrosGuardados();
                    break;
                case 3:
                    verAutoresregistrados();
                    break;
                case 4:
                    verAutoresVivosEnCiertoAnio();
                    break;
                case 5:
                    verLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");

            }
        }
    }

    private void verLibrosPorIdioma() {
        System.out.println("""
                Elige el idioma del que quieres ver titulos:
                es - Español
                en - Inglés
                fr - Francés
                pt - Portugués
                """);
        var idioma = teclado.nextLine();
        libros = repositorio.libroPorIdioma(Collections.singletonList(idioma));
        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    private void verAutoresVivosEnCiertoAnio() {
        System.out.println("Ingresa fecha de autores que quieres consultar:");
        var anio = teclado.nextInt();
        autores = repositorio.autoresHastaFecha(anio);
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);

    }

    private void verAutoresregistrados() {
        autores = repositorio.autores();
        autores.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(System.out::println);
    }

    private void verLibrosGuardados() {
        libros = repositorio.findAll();

        libros.stream()
                .sorted(Comparator.comparing(Libro::getTitulo))
                .forEach(System.out::println);
    }

    private static String encodeValue(String value){
        try {
            return URLEncoder.encode(value, StandardCharsets.US_ASCII.toString());
        } catch (UnsupportedEncodingException e){
            throw new RuntimeException(e.getCause());
        }
    }

    private void buscarLibro(){
        System.out.println("¿Que libro deseas buscar?");
        var nombreLibroRequerido = teclado.nextLine();
        var nombreLibro = encodeValue(nombreLibroRequerido);
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro);
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);

        Optional<DatosLibro> libroBuscado = datosBusqueda.resultado().stream()
                .filter(l -> l.titulo().toLowerCase().contains(nombreLibroRequerido.toLowerCase()))
                .findFirst();

        if(libroBuscado.isPresent()){
            String tituloLibro = libroBuscado.get().titulo();
            Optional<Libro> libroExistente = repositorio.libroPorTitulo(tituloLibro);

            if (libroExistente.isPresent()) {
                System.out.println(libroExistente.get());
            } else {
                System.out.println(libroBuscado.get());
                Libro libro = new Libro(libroBuscado.get());
                repositorio.save(libro);
                System.out.println("Libro guardado en la base de datos.");
            }
        } else {
            System.out.println("Libro no encontrado...");
        }
    }
}
