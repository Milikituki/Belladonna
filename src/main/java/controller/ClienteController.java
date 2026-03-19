package controller;

import model.Cliente;

import java.util.Scanner;

public class ClienteController {
    private Scanner sc;
    public ClienteController(Scanner sc) {
        this.sc = sc;
    }
    public void gestionarClientes(){
        int opcion;
                do {
            System.out.printf("""
                --- CLIENTES ---
                1. Ver todos los clientes
                2. Añadir un cliente
                3. Modificar un cliente
                4. Eliminar un cliente
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
