/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.persistencia.ClienteDAO;

/**
 *
 * @author Asus
 */
public class servicioCliente {

    servicioMenu servMenu;
    ClienteDAO dao;
    Scanner leer;

    public servicioCliente() {
        dao = new ClienteDAO();
        leer = new Scanner(System.in);
        servMenu = new servicioMenu();
    }

    public Cliente crearCliente() throws Exception {
//        private Integer id;
//    private Long dni;
//    private String nombre;
//    private String apellido;
//    private String telefono;
        Cliente cliente = new Cliente();

        System.out.println("Ingrese el DNI del cliente");
        long dni = leer.nextInt();
        //validar la existencia del cliente
        cliente = dao.buscarPorDNI(dni);
        if (!(cliente == null)) {
            System.out.println("El cliente ya existe");
            System.out.println(cliente);
            servMenu.menu();
        } else {
            cliente.setDni(dni);
            System.out.println("Ingrese el nombre del cliente");
            String nombre = leer.next();
            cliente.setNombre(nombre);
            System.out.println("Apellido:...");
            String apellido = leer.next();
            cliente.setApellido(apellido);
            System.out.println("Ingrese el telefono");
            String telefono = leer.next();
            cliente.setTelefono(telefono);
        }
        return cliente;
    }
    
    public void guardarCliente() throws Exception {
        dao.guardar(crearCliente());
    }
    
    

}
