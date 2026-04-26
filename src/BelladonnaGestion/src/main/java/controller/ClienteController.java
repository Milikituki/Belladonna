package controller;
import dao.ClienteDAO;
import database.SchemDB;
import model.Cliente;

import java.sql.SQLException;
import java.util.Scanner;

public class ClienteController {
    private ClienteDAO clienteDAO;
    private Scanner sc;
    public ClienteController(Scanner sc) {
        this.sc = sc;
        clienteDAO = new ClienteDAO();
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
                case 1 -> clienteDAO.obtenerClientes().forEach(System.out::println);
                case 2 -> {
                    sc.nextLine(); //aquí queda un salto de línea pendiente, lo solucionamos con este nextLine y así no se recoge enn la variable "nombre".
                    System.out.println("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.println("Teléfono: ");
                    String telefono = sc.next();
                    System.out.println("Correo: ");
                    String correo = sc.next();
                    try {
                        clienteDAO.insertarCliente(new Cliente(nombre, telefono, correo));
                        System.out.println("El cliente se ha registrado correctamente");
                    } catch (SQLException e) {
                        System.out.println("No se ha podido registrar el cliente");
                    }
                    sc.nextLine();
                }
                case 3 -> {
                    clienteDAO.obtenerClientes().forEach(System.out::println);
                    System.out.println("Indica el campo que quieres modificar");
                    System.out.println("1. Nombre");
                    System.out.println("2. Teléfono");
                    System.out.println("3. Correo");
                    int campo = sc.nextInt();
                    switch (campo) {
                        case 1 -> menuModificarCliente(SchemDB.COL_NOMBRE);
                        case 2 -> menuModificarCliente(SchemDB.COL_TELEFONO);
                        case 3 -> menuModificarCliente(SchemDB.COL_EMAIL);
                    }
                }
                case 4 -> {
                    clienteDAO.obtenerClientes().forEach(System.out::println);
                    System.out.println("Indica el ID del cliente que quieres eliminar: ");
                    int clienteID = sc.nextInt();
                    try {
                        clienteDAO.eliminarCliente(clienteID);
                        System.out.println("Cliente eliminado correctamente");
                    } catch (SQLException e) {
                        System.out.println("No se ha podido eliminar el cliente");
                    }

                }
                case 5 -> {}
                default -> System.out.println("Opción incorrecta");
            }
        }while(opcion!=5);
    }
    public void menuModificarCliente(String columna){
        System.out.println("Indica el ID del cliente que quieres modificar");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Valor nuevo: ");
        String nombre = sc.nextLine();
        try {
            clienteDAO.actualizarCliente(id, columna, nombre);
            System.out.println("Cliente modificado correctamente");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
