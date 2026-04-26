package controller;

import dao.EmpleadoDAO;

import database.SchemDB;
import model.Empleado;


import java.sql.SQLException;
import java.util.Scanner;

public class EmpleadoController {
    private Scanner sc;
    private EmpleadoDAO empleadoDAO;
    public EmpleadoController() {
        empleadoDAO = new EmpleadoDAO();
    }
    public EmpleadoController(Scanner sc) {
        this.sc = sc;
        this.empleadoDAO = new EmpleadoDAO();
    }
    public void gestionarEmpleados() {
        int opcion;
        do {
            System.out.printf("""
                --- EMPLEADOS ---
                1. Ver todos los empleados
                2. Añadir un empleado
                3. Modificar un empleado
                4. Eliminar un empleado
                5. Volver atrás""");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> {empleadoDAO.obtenerEmpleados().forEach(System.out::println);}
                case 2 -> {
                    sc.nextLine();
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Especialidad: ");
                    String especialidad = sc.nextLine();
                    try {
                        empleadoDAO.insertarEmpleado(new Empleado(nombre, especialidad));
                        System.out.println("Empleado agregado correctamente");
                    } catch (SQLException e) {
                        System.out.println("No se ha podido agregar el empleado");
                    }
                    sc.nextLine();
                }
                case 3 -> {
                    empleadoDAO.obtenerEmpleados().forEach(System.out::println);
                    System.out.println("Indica el campo que quieres modificar: ");
                    System.out.println("1. Nombre");
                    System.out.println("2. Especialidad");
                    int campo = sc.nextInt();
                    switch (campo) {
                        case 1 -> menuModificar(SchemDB.COL_NOMBRE);
                        case 2 -> menuModificar(SchemDB.COL_ESPECIALIDAD);
                    }
                }
                case 4 -> {
                    empleadoDAO.obtenerEmpleados().forEach(System.out::println);
                    System.out.println("Indica el ID del empleado que quieres eliminar: ");
                    int id = sc.nextInt();
                    try {
                        empleadoDAO.eliminarEmpleado(id);
                        System.out.println("Empleado eliminado correctamente");
                    } catch (SQLException e) {
                        System.out.println("No se ha podido eliminar el empleado");
                    }
                }
                case 5 -> {}
                default -> System.out.println("Opción incorrecta");
            }
        }while(opcion!=5);
    }
    public void menuModificar(String columna){
        System.out.println("Indica el ID del empleado que quieres modificar");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Valor nuevo: ");
        String nombre = sc.nextLine();
        try {
            empleadoDAO.actualizarEmpleado(id, columna, nombre);
            System.out.println("Valor modificado correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
