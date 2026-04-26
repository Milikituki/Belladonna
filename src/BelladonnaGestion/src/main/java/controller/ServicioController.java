package controller;

import dao.ServicioDAO;
import database.SchemDB;
import model.Servicio;

import java.sql.SQLException;
import java.util.Scanner;

public class ServicioController {
    private Scanner sc;
    private ServicioDAO servicioDAO;
    public ServicioController(Scanner sc) {
        this.sc = sc;
        servicioDAO = new ServicioDAO();
    }
    public void gestionarServicios() {
        int opcion;
        do {
            System.out.printf("""
                --- SERVICIOS ---
                1. Ver todos los servicios
                2. Añadir un servicio
                3. Modificar un servicio
                4. Eliminar un servicio
                5. Volver atrás""");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> {servicioDAO.obtenerServicios().forEach(System.out::println);}
                case 2 -> {
                    sc.nextLine();
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Duracion: ");
                    int duracion = sc.nextInt();
                    System.out.println("Precio: ");
                    double precio = sc.nextDouble();
                    try {
                        servicioDAO.insertarServicio(new Servicio(nombre, duracion, precio));
                        System.out.println("Servicio agregado correctamente");
                    } catch (SQLException e) {
                        System.out.println("No se ha podido agregar el servicio");
                    }
                    sc.nextLine();
                }
                case 3 -> {
                    servicioDAO.obtenerServicios().forEach(System.out::println);
                    System.out.println("Indica el campo que quieres modificar: ");
                    System.out.println("1. Nombre");
                    System.out.println("2. Duración");
                    System.out.println("3. Precio");
                    int campo = sc.nextInt();
                    switch (campo) {
                        case 1 -> menuModificar(SchemDB.COL_NOMBRE);
                        case 2 -> menuModificar(SchemDB.COL_DURACION);
                        case 3 -> menuModificar(SchemDB.COL_PRECIO);
                    }
                }
                case 4 -> {
                    servicioDAO.obtenerServicios().forEach(System.out::println);
                    System.out.println("Indica el ID del servicio que quieres eliminar: ");
                    int id = sc.nextInt();
                    try {
                        servicioDAO.eliminarServicio(id);
                        System.out.println("Servicio eliminado correctamente");
                    } catch (SQLException e) {
                        System.out.println("No se ha podido eliminar el servicio");
                    }
                }
                case 5 -> {}
                default -> System.out.println("Opción incorrecta");
            }
        }while(opcion!=5);
    }
    public void menuModificar(String columna){
        System.out.println("Indica el ID del servicio que quieres modificar");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Valor nuevo: ");
        String nombre = sc.nextLine();
        try {
            servicioDAO.actualizarServicio(id, columna, nombre);
            System.out.println("Valor modificado correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
