package controller;

import dao.CitaDAO;
import database.SchemDB;
import model.Cita;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class CitaController {
    private Scanner sc;
    private CitaDAO citaDAO;
    public CitaController(Scanner sc) {
        this.sc = sc;
        this.citaDAO = new CitaDAO();
    }
    public void gestionarCitas(){
        int opcion;
        do {
            System.out.printf("""
                --- CITAS ---
                1. Ver todas las citas
                2. Añadir una cita
                3. Modificar una cita
                4. Eliminar una cita                
                5. Ver citas de HOY
                6. Volver atrás""");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> {
                    try {
                        citaDAO.obtenerCitasDetalladas(null).forEach(System.out::println);
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case 2 -> {
                    sc.nextLine();
                    System.out.println("ID Cliente: ");
                    int idCliente = sc.nextInt();
                    System.out.println("ID Servicio: ");
                    int idServicio = sc.nextInt();
                    System.out.println("ID Empleado: ");
                    int idEmpleado = sc.nextInt();
                    System.out.println("Fecha (aaaa-mm-dd): ");
                    String fechaStr = sc.next();
                    LocalDate fecha =  LocalDate.parse(fechaStr);
                    System.out.println("Hora (hh:mm): ");
                    String horaStr = sc.next();
                    LocalTime hora = LocalTime.parse(horaStr);
                    try {
                        citaDAO.insertarCita(new Cita(idCliente, idServicio, idEmpleado, fecha, hora));
                        System.out.println("Cita agregada correctamente");
                    } catch (SQLException e) {
                        System.out.println("No se ha podido agregar la cita");
                    }
                    sc.nextLine();
                }
                case 3 -> {
                    citaDAO.obtenerCitas().forEach(System.out::println);
                    System.out.println("Indica el campo que quieres modificar: ");
                    System.out.printf("""
                            1. ID Cliente
                            2. ID Servicio
                            3. ID Empleado
                            4. Fecha
                            5. Hora""");
                    System.out.println();
                    int campo =  sc.nextInt();
                    switch (campo) {
                        case 1 -> menuModificar(SchemDB.COL_IDCLIENTE);
                        case 2 -> menuModificar(SchemDB.COL_IDSERVICIO);
                        case 3 -> menuModificar(SchemDB.COL_IDEMPLEADO);
                        case 4 -> menuModificar(SchemDB.COL_FECHA);
                        case 5 -> menuModificar(SchemDB.COL_HORA);
                    }
                }
                case 4 -> {
                    citaDAO.obtenerCitas().forEach(System.out::println);
                    System.out.println("Indica el ID de la cita que quieres eliminar: ");
                    int id = sc.nextInt();
                    try {
                        citaDAO.eliminarCita(id);
                        System.out.println("Cita eliminada correctamente");
                    } catch (SQLException e) {
                        System.out.println("No se ha podido eliminar la cita");
                    }
                }
                case 5 -> {
                    try {
                        List<String> citasHoy = citaDAO.obtenerCitasDetalladas(LocalDate.now());
                        if (citasHoy.isEmpty()) {
                            System.out.println("No hay citas programadas para hoy");
                        } else {
                            citasHoy.forEach(System.out::println);
                        }
                    } catch (SQLException e) {
                        System.out.println("Error al obtener las citas: " + e.getMessage());
                    }
                }
                case 6 -> {}
                default -> System.out.println("Opción incorrecta");
            }
        }while(opcion!=5);
    }
    public void menuModificar(String columna){
        System.out.println("Indica el ID de la cita que quieres modificar");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Valor nuevo: ");
        String nombre = sc.nextLine();
        try {
            citaDAO.actualizarCita(id, columna, nombre);
            System.out.println("Valor modificado correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
