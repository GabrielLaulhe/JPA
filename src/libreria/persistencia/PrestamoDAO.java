/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Prestamo;

/**
 *
 * @author Asus
 */
public class PrestamoDAO extends DAO<Prestamo>{
    
    @Override
    public void guardar(Prestamo prestamo) {
        super.guardar(prestamo);
    }

    public void eliminar(Integer id) throws Exception {
        Prestamo prestamo = buscarPorId(id);
        super.eliminar(prestamo);
    }
    
    public void eliminarPorPrestamo(Prestamo prestamo) throws Exception {
        super.eliminar(prestamo);
    }

    public List<Prestamo> listarTodos() throws Exception {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p ").getResultList();
        desconectar();
        return prestamos;
    }
    
    public Prestamo buscarPorId(Integer id) throws Exception {
        conectar();
        Prestamo prestamo = (Prestamo) em.createQuery("SELECT p FROM Prestamo p WHERE p.id LIKE :id").setParameter("id", id).getSingleResult();
        desconectar();
        return prestamo;
    }
    
    public List<Prestamo> buscarPorCliente(String apellido) {
        conectar();
        List<Prestamo> prestamos = em.createNamedQuery("SELECT p FROM Prestamo p WHERE p.cliente.apellido LIKE :apellido").setParameter("apellido", "%" + apellido + "%").getResultList();
        desconectar();
        return prestamos;
    }
    
    public List<Prestamo> buscarPorDniCliente(long dni) {
        conectar();
        List<Prestamo> prestamos = em.createNamedQuery("SELECT p FROM Prestamo p WHERE p.cliente.dni LIKE :dni").setParameter("dni", "%" + dni + "%").getResultList();
        desconectar();
        return prestamos;
    }
    
}
