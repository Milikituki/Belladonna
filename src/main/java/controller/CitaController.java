package controller;

import java.util.Scanner;

public class CitaController {
    private Scanner sc;
    public CitaController(Scanner sc) {
        this.sc = sc;
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
