import controller.CitaController;
import controller.ClienteController;
import controller.EmpleadoController;
import controller.ServicioController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClienteController clienteController = new ClienteController(sc);
        ServicioController servicioController = new ServicioController(sc);
        EmpleadoController empleadoController = new EmpleadoController(sc);
        CitaController citaController = new CitaController(sc);

        int opcion;
        do {
            System.out.printf("""
                    --- BELLADONNA ESTÉTICA ---
                    1. Gestionar clientes
                    2. Gestionar servicios
                    3. Gestionar empleados
                    4. Gestionar citas
                    5. Salir""");
            System.out.println();
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> {
                    clienteController.gestionarClientes();
                }
                case 2 -> {
                    servicioController.gestionarServicios();
                }
                case 3 -> {
                    empleadoController.gestionarEmpleados();
                }
                case 4 -> {
                    citaController.gestionarCitas();
                }
                case 5 -> {
                    System.out.println("Saliendo...");
                    sc.close();
                }
                default -> {
                    System.out.println("Opción incorrecta");
                }
            }
        }while(opcion!=5);
    }
}
