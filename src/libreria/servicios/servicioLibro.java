/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.LibroDAO;

/**
 *
 * @author Asus
 */
public class servicioLibro {

//    private Long isbn;
//    private String titulo;
//    private Integer anio;
//    private Integer ejemplares;
//    private Integer ejemplaresPrestados;
//    private Integer ejemplaresRestantes;
//    private Boolean alta = true;
//    @ManyToOne
//    private Autor autor;
//    @ManyToOne
//    private Editorial editorial;
    Scanner leer = new Scanner(System.in).useDelimiter("\n");

    LibroDAO dao;
    servicioAutor sa;
    servicioEditorial se;

    public servicioLibro() {
        sa = new servicioAutor();
        se = new servicioEditorial();
        dao = new LibroDAO();
    }

        
    public Libro crearLibro() throws Exception {
        System.out.println("Ingrese el isbn");
        long isbn = leer.nextLong();
        System.out.println("Ingrese el titulo del libro");
        String titulo = leer.next();
        System.out.println("Ingrese el anio");
        //leer.next();
        int anio = Integer.parseInt(leer.next()); // PARSEINT ES CUANDO ES INT Y VALUEOF CUANDO ES INTEGER
        //int anio = leer.nextInt();
        System.out.println("Ingrese la cantidad de ejemplares que hay en stock");
        int cantEjemplares = Integer.parseInt(leer.next());

        Autor a = sa.validarAutor();

        System.out.println("Ingrese el nombre de la editorial");
        String nombreEditorial = leer.next();
        System.out.println(nombreEditorial);
        Editorial e = se.validarEditorial(nombreEditorial);
        
        return new Libro(isbn, titulo, anio, cantEjemplares, 0, cantEjemplares, a, e);
    }

    public void guardarLibro() throws Exception {
        Libro libro = crearLibro();
        dao.guardar(libro);
    }

    public Libro buscarLibroISBN(Long isbn) throws Exception {
        Libro libro = null;
        try {
            libro = dao.buscarPorISBN(isbn);
            return libro;
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return libro;
        }
    }

    public Libro buscarLibroTitulo() {
        try {
            System.out.println("Ingrese el titulo del libro");
            String titulo = leer.next();
            if (titulo == null) {
                System.out.println("Debe ingresar un nombre");
                buscarLibroTitulo();
            }
            return dao.buscarPorNombre(titulo);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }
    }

    /**
     *
     * @return
     */
    public Collection<Libro> buscarLibroPorAutor() {

        try {
            System.out.println("Ingrese el autor");
            String autor = leer.next();
            if (autor == null) {
                System.out.println("Debe ingresar un nombre");
                buscarLibroPorAutor();
            }
            return dao.buscarPorAutor(autor);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }
    }
    
    public Collection<Libro> buscarLibroPorEditorial() {

        try {
            System.out.println("Ingrese la editorial ");
            String editorial = leer.next();
            if (editorial == null) {
                System.out.println("Debe ingresar un nombre");
                buscarLibroPorEditorial();
            }
            return dao.buscarPorAutor(editorial);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }
    }

    public void mostrarListaLibroAutor() {
        ArrayList<Libro> listaLibro = (ArrayList<Libro>) buscarLibroPorAutor();
        for (Libro libros : listaLibro) {
            System.out.println(libros);
        }
    }
    
    public void mostrarListaLibroEditorial() {
        ArrayList<Libro> listaLibro = (ArrayList<Libro>) buscarLibroPorEditorial();
        for (Libro libros : listaLibro) {
            System.out.println(libros);
        }
    }
}
