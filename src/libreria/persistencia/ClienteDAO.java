/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Cliente;

/**
 *
 * @author Asus
 */
public class ClienteDAO extends DAO<Cliente>{
    
    @Override
    public void guardar(Cliente cliente) {
        super.guardar(cliente);
    }

    public void eliminar(String id) throws Exception {
        Cliente cliente = buscarPorId(id);
        super.eliminar(cliente);
    }

    public List<Cliente> listarTodos() throws Exception {
        conectar();
        List<Cliente> clientes = em.createQuery("SELECT c FROM Cliente c ").getResultList();
        desconectar();
        return clientes;
    }
    
    public Cliente buscarPorId(String id) throws Exception {
        conectar();
        Cliente clientes = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.id LIKE :id").setParameter("id", id).getSingleResult();
        desconectar();
        return clientes;
    }
    
    public Cliente buscarPorDNI(Long dni) throws Exception {
        conectar();
        Cliente clientes = (Cliente) em.createQuery("SELECT c FROM Cliente c WHERE c.dni LIKE :dni").setParameter("dni", dni).getSingleResult();
        desconectar();
        return clientes;
    }
}
