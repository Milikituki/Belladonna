package controller;

import java.util.Scanner;

public class EmpleadoController {
    private Scanner sc;
    public EmpleadoController(Scanner sc) {
        this.sc = sc;
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
