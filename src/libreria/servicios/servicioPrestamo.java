/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.ClienteDAO;
import libreria.persistencia.LibroDAO;
import libreria.persistencia.PrestamoDAO;

/**
 *
 * @author Asus
 */
public class servicioPrestamo {

//    private Integer id; AUTOGENERADO
//    private LocalDate fechaPrestamo;
//    private LocalDate fechaDevolucion;
//    @OneToOne
//    private Libro libro;
//    @OneToOne
//    private Cliente cliente;
    PrestamoDAO dao;
    LibroDAO daoL;
    Scanner leer;
    servicioMenu servMenu;
    ClienteDAO daoC;
    servicioCliente servC;

    public servicioPrestamo() {
        dao = new PrestamoDAO();
        leer = new Scanner(System.in);
        daoL = new LibroDAO();
        servMenu = new servicioMenu();
        daoC = new ClienteDAO();
        servC = new servicioCliente();
    }

    public Prestamo crearPrestamos() throws Exception {
        Collection<Libro> listaLibro = new ArrayList();
        Libro libro = new Libro();
        Prestamo prestamo = new Prestamo();

        // VALIDACION DEL LIBRO Y ASIGNACION A PRESTAMO SI EXISTE
        System.out.println("Ingrese el nombre del libro que desea llevar");
        String nombre = leer.next();
        listaLibro = daoL.buscarLibrosPorNombre(nombre);
        if (listaLibro.isEmpty()) {
            System.out.println("No existe ningun libro con ese nombre");
            servMenu.menu();
        } else {
            System.out.println("De los siguientes libros, elija uno e ingrese el isbn");
            for (Libro libros : listaLibro) {
                System.out.println(libros);
            }
            boolean aux = false;
            while (!aux) {
                long isbn = leer.nextLong();
                libro = daoL.buscarPorISBN(isbn);
                if (Objects.isNull(libro) || !listaLibro.contains(libro)) {
                    System.out.println("Debe ingresar el codigo del libro valido segun la lista");
                } else {
                    prestamo.setLibro(libro);
                    libro.setEjemplaresPrestados(libro.getEjemplaresPrestados() + 1);
                    libro.setEjemplaresRestantes(libro.getEjemplaresRestantes() - 1);
                    aux = true;
                }
            }
        }

        // VALIDAR CLIENTE
        Cliente cliente = validarCliente();
        if (cliente == null) {
            System.out.println("Debe crear un nuevo cliente");
            servC.guardarCliente();
        } else {
            prestamo.setCliente(cliente);
        }

        // INGRESANDO EL RESTO DE LOS ATRIBUTOS
        // FECHA PRESTAMO
        boolean aux1 = false;
        while (!aux1) {
            System.out.println("Ingrese la fecha del prestamo en formato AAAA-MM-DD");
            String fecha = leer.nextLine();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaP = LocalDate.parse(fecha, formato);
            LocalDate fechaActual = LocalDate.now();
            if (fechaP.isAfter(fechaActual) || fechaP.isEqual(fechaActual)) {
                prestamo.setFechaPrestamo(fechaP);
                aux1 = true;
            } else {
                System.out.println("Debe ingresar una fecha igual o posterior a la fecha actual");
            }
        }

        // FECHA DE DEVOLUCION
        boolean aux2 = false;
        while (!aux2) {
            System.out.println("Ingrese la fecha de devolucion AAAA-MM-DD posterior a: " + prestamo.getFechaPrestamo());
            String fechaD = leer.nextLine();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaDev = LocalDate.parse(fechaD, formato);
            if (fechaDev.isAfter(prestamo.getFechaPrestamo())) {
                prestamo.setFechaPrestamo(fechaDev);
                aux2 = true;
            }
        }
        return prestamo;
    }

    public void guardarPrestamo() throws Exception {
        dao.guardar(crearPrestamos());
    }

    public Cliente validarCliente() throws Exception {

        System.out.println("Ingrese el dni del cliente");
        long dni = leer.nextLong();
        Cliente cliente = daoC.buscarPorDNI(dni);

        if (cliente == null) {
            System.out.println("El cliente ingresado no existe");
            System.out.println("Desea ingresar nuevamente el dni: s/n");
            String opc = leer.next();
            if (opc.equalsIgnoreCase("s")) {
                validarCliente();
            } else {
                return null;
            }
        }
        return cliente;
    }
    
    public Prestamo buscarPorId() throws Exception {
        Prestamo prestamo = new Prestamo();
        System.out.println("Ingrese el id del Prestamo");
        Integer id = leer.nextInt();
        prestamo = dao.buscarPorId(id);
        if (prestamo == null) {
            System.out.println("El prestamo no existe con ese id");
        }
        return prestamo;
    }
    
    public List<Prestamo> buscarPorCliente(String apellido) {
        return dao.buscarPorCliente(apellido);
    }
    
    public List<Prestamo> buscarPorDniCliente(Long dni) {
        return dao.buscarPorDniCliente(dni);
        
    }
    
    public void eliminarPrestamo(Prestamo prestamo) throws Exception {
        dao.eliminarPorPrestamo(prestamo);
        
    }
    
    public void buscarPrestamoCliente() {
        System.out.println("Ingrese el dni del cliente");
                Long dni = leer.nextLong();
                List<Prestamo> prestamos = buscarPorDniCliente(dni);
        for (Prestamo listPrestamoDni : prestamos) {
            System.out.println(listPrestamoDni);
        }
        
    }

    public void menuDevolucionPrestamo() throws Exception {

        System.out.println("DEVOLUCION DE PRESTAMO");
        System.out.println("Busqueda de prestamo por: ");
        System.out.println("1 - Id del Prestamo\n"
                + "2 - Por DNI del cliente\n"
                + "3 - Por apellido del cliente\n"
                + "4 - Volver al menu anterior");
        System.out.println("Ingrese una opcion");
        Integer opc = leer.nextInt();
        switch (opc) {
            case 1:
                Prestamo p = buscarPorId();
                if (p==null) {
                    System.out.println("Intente buscar por otra opcion");
                    menuDevolucionPrestamo();
                }
                System.out.println(p);
                LocalDate fechaActual = LocalDate.now();
                if (fechaActual.isAfter(p.getFechaDevolucion())) {
                    System.out.println("Hubo un retraso en la devolucion, debera pagar una multa");
                }
                p.getLibro().setEjemplaresPrestados(p.getLibro().getEjemplaresPrestados()-1);
                p.getLibro().setEjemplaresRestantes(p.getLibro().getEjemplaresRestantes()+1);
                eliminarPrestamo(p);
                break;
            case 2:
                System.out.println("Ingrese el dni del cliente");
                Long dni = leer.nextLong();
                List<Prestamo> prestamosDni = buscarPorDniCliente(dni);
                for (Prestamo listPrestamoDni : prestamosDni) {
                    System.out.println(listPrestamoDni);
                }
                System.out.println("Teniendo en cuenta la lista anterior...");
                Prestamo p2 = buscarPorId();
                if (p2==null) {
                    System.out.println("Intente buscar por otra opcion");
                    menuDevolucionPrestamo();
                }
                System.out.println(p2);
                LocalDate fechaActual2 = LocalDate.now();
                if (fechaActual2.isAfter(p2.getFechaDevolucion())) {
                    System.out.println("Hubo un retraso en la devolucion, debera pagar una multa");
                }
                p2.getLibro().setEjemplaresPrestados(p2.getLibro().getEjemplaresPrestados()-1);
                p2.getLibro().setEjemplaresRestantes(p2.getLibro().getEjemplaresRestantes()+1);
                eliminarPrestamo(p2);
                break;
            case 3:
                System.out.println("Ingrese el apellido del cliente");
                String apellido = leer.next();
                List<Prestamo> prestamos = buscarPorCliente(apellido);
                for (Prestamo listaPrestamo : prestamos) {
                    System.out.println(listaPrestamo);
                }
                System.out.println("teniendo en cuenta la lista anterior...");
                Prestamo p1 = buscarPorId();
                if (p1==null) {
                    System.out.println("Intente buscar por otra opcion");
                    menuDevolucionPrestamo();
                }
                System.out.println(p1);
                LocalDate fechaActual1 = LocalDate.now();
                if (fechaActual1.isAfter(p1.getFechaDevolucion())) {
                    System.out.println("Hubo un retraso en la devolucion, debera pagar una multa");
                }
                p1.getLibro().setEjemplaresPrestados(p1.getLibro().getEjemplaresPrestados()-1);
                p1.getLibro().setEjemplaresRestantes(p1.getLibro().getEjemplaresRestantes()+1);
                eliminarPrestamo(p1);
                break;
            case 4:
                servMenu.menu();
                break;
            default:
                System.out.println("Error, opcion incorrecta");
                menuDevolucionPrestamo();
        }
    }
}
