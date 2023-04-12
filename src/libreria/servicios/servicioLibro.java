/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

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
    Scanner leer = new Scanner(System.in);
    
    LibroDAO dao;
    servicioAutor sa;
    servicioEditorial se;
    public servicioLibro() {
        sa = new servicioAutor();
        se = new servicioEditorial();
        dao = new LibroDAO();
    }
    
    public Libro crearLibro() throws Exception {
        System.out.println("Ingrese el titulo del libro");
        String titulo = leer.next();
        System.out.println("Ingrese el anio");
        int anio = leer.nextInt();
        System.out.println("Ingrese la cantidad de ejemplares que hay en stock");
        int cantEjemplares = leer.nextInt();
        
        
        Autor a = sa.validarAutor();
        
        System.out.println("Ingrese el nombre de la editorial");
        String nombre1 = leer.next();
        Editorial e = se.validarEditorial(nombre1);
        return new Libro(titulo, anio, cantEjemplares, a, e);  
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
            if (titulo==null) {
                System.out.println("Debe ingresar un nombre");
                buscarLibroTitulo();
            }
            return dao.buscarPorNombre(titulo);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return null;
        }
    }
    
    public Libro buscarLibroPorAutor() {
        
        try {
            System.out.println("Ingrese el autor");
            String autor = leer.next();
            if (autor==null) {
                System.out.println("Debe ingresar un nombre");
                buscarLibroPorAutor();
            }
            
        } catch (Exception e) {
        }
        
    }
}
