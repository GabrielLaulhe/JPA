/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.persistencia;

import java.util.Collection;
import java.util.List;
import libreria.entidades.Libro;

/**
 *
 * @author Asus
 */

public class LibroDAO extends DAO<Libro> {

    @Override
    public void guardar(Libro libro) {
        super.guardar(libro);
    }

    public void eliminar(Long isbn) throws Exception {
        Libro libro = buscarPorISBN(isbn);
        super.eliminar(libro);
    }

    public List<Libro> listarTodos() throws Exception {
        conectar();
        List<Libro> libroes = em.createQuery("SELECT l FROM Libro l ").getResultList();
        desconectar();
        return libroes;
    }

    public Libro buscarPorISBN(Long isbn) throws Exception {
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn").setParameter("isbn", isbn).getSingleResult();
        desconectar();
        return libro;
    }
    
    public Collection<Libro> buscarLibrosPorNombre(String titulo) throws Exception {
        conectar();
        Collection<Libro> libros;
        libros = (Collection<Libro>) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo").setParameter("titulo", "%" + titulo+ "%").getSingleResult();
        desconectar();
        return libros;
    }
    
    public Libro buscarPorNombre(String titulo) throws Exception {
        conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo").setParameter("titulo", "%" + titulo+ "%").getSingleResult();
        desconectar();
        return libro;
    }
    
    public Collection<Libro> buscarPorAutor(String nombre) throws Exception {
        conectar();
        Collection<Libro> listaLibro;
        listaLibro = (Collection<Libro>) (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.autor.nombre LIKE :nombre").setParameter("nombre", "%" + nombre + "%").getResultList();
        desconectar();
        return listaLibro;
    }
    
    public Collection<Libro> buscarPorEditorial(String nombre) throws Exception {
        conectar();
        Collection<Libro> listaLibro;
        listaLibro = (Collection<Libro>) (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.editorial.nombre LIKE :nombre").setParameter("nombre", "%" + nombre + "%").getResultList();
        desconectar();
        return listaLibro;
    }
}
