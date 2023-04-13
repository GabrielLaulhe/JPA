/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package libreria.servicios;

import java.util.Scanner;
import libreria.entidades.Libro;

/**
 *
 * @author Asus
 */
public class servicioMenu {

    Scanner leer = new Scanner(System.in);
    servicioAutor servA;
    servicioLibro servL;

    public servicioMenu() {
        servA = new servicioAutor();
        servL = new servicioLibro();
    }

    public void menu() {

        try {

            System.out.println("------------MENU BIBLIOTECA-------------");
            System.out.println("Ingrese alguna de las siguientes opciones");
            System.out.println("1) Búsqueda de un Autor por nombre.\n"
                    + "2) Búsqueda de un libro por ISBN.\n"
                    + "3) Búsqueda de un libro por Título.\n"
                    + "4) Búsqueda de un libro/s por nombre de Autor.\n"
                    + "5) Búsqueda de un libro/s por nombre de Editorial\n"
                    + "6) Salir.");

            Integer opc = leer.nextInt();
            switch (opc) {
                case 1:
                    servA.buscarAutorNombre();
                    menu();
                    break;
                case 2:
                try {
                    Libro libro = new Libro();
                    System.out.println("Ingrese el isbn");
                    long isbn = leer.nextLong();
                    libro = servL.buscarLibroISBN(isbn);
                    if (libro == null) {
                        System.out.println("El libro ingresado no existe");
                    } else {
                        System.out.println(libro);
                    }
                } catch (Exception e) {
                    System.out.println("Error" + e.getMessage());
                }
                menu();
                break;
                case 3:
                    servL.buscarLibroTitulo();
                    menu();
                    break;
                case 4:
                    servL.mostrarListaLibroAutor();
                    menu();
                    break;
                case 5:
                    servL.mostrarListaLibroEditorial();
                    menu();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Error");
                    menu();
            }

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            menu();
        }
    }
}
