package controller;

import java.util.Scanner;

public class ServicioController {
    private Scanner sc;
    public ServicioController(Scanner sc) {
        this.sc = sc;
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
                case 1 -> {}
                case 2 -> {}
                case 3 -> {}
                case 4 -> {}
                case 5 -> {}
                default -> System.out.println("Opción incorrecta");
            }
        }while(opcion!=5);
    }
}
