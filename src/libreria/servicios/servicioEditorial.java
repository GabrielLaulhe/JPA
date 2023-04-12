/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;


/**
 *
 * @author Asus
 */
public class servicioEditorial {
    Scanner leer = new Scanner(System.in);
    EditorialDAO dao;
    
    public servicioEditorial() {
        dao = new EditorialDAO();
    }

            
    public Editorial guardarEditorial(String nombre1) {
        Editorial editorial = null;
        try {
            
            editorial = dao.buscarPorNombre(nombre1);
            return editorial;
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return editorial;
        }
    }
    
//    public Editorial buscarEditorialId(Integer id) {
//       
//    }
    
    public Editorial buscarEditorialNombre(String nombre) throws Exception {
        Editorial e = null;
        e = dao.buscarPorNombre(nombre);
        return e;
    }
    
    public Editorial validarEditorial(String nombre1) throws Exception {
        Editorial e = buscarEditorialNombre(nombre1);
        if (e == null) {
           return guardarEditorial(nombre1);
        }else {
            return e;
        }
    }
    
}
