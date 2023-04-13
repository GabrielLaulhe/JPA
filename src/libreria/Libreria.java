package libreria;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.servicios.servicioAutor;
import libreria.servicios.servicioEditorial;
import libreria.servicios.servicioLibro;

/**
 *
 * @author {Gabi}
 */

public class Libreria {

    public static Scanner leer = new Scanner(System.in).useDelimiter("\n");

    public static void main(String[] args) throws Exception {

//        servicioAutor sa = new servicioAutor();
//        servicioEditorial se = new servicioEditorial();
//        servicioLibro sl = new servicioLibro();
//        //EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibreriaPU");
        //EntityManager em = emf.createEntityManager();
        
       // se.guardarEditorial("uno");
        //se.guardarEditorial("dos");
        servicioLibro servL = new servicioLibro();
        long l = 1;
        servL.buscarLibroISBN(l);
        
    }

}
