package dao;

import database.DBConnection;
import database.SchemDB;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection conexion;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public ClienteDAO() {
        try {
            conexion = DBConnection.getConexion();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Cliente> obtenerClientes() {
        System.out.println("Iniciando obtencion de clientes...");
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM " + SchemDB.TAB_CLIENTES;
        try {
            preparedStatement = conexion.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(SchemDB.COL_IDCLIENTE);
                String nombre = resultSet.getString(SchemDB.COL_NOMBRE);
                String telefono = resultSet.getString(SchemDB.COL_TELEFONO);
                String email = resultSet.getString(SchemDB.COL_EMAIL);
                clientes.add(new Cliente(id, nombre, telefono, email));
            }
            System.out.println("Clientes totales: "+clientes.size());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clientes;
    }

    public void insertarCliente(Cliente cliente) throws SQLException {
        String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)", SchemDB.TAB_CLIENTES, SchemDB.COL_NOMBRE, SchemDB.COL_TELEFONO, SchemDB.COL_EMAIL);
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, cliente.getNombre());
        preparedStatement.setString(2, cliente.getTelefono());
        preparedStatement.setString(3, cliente.getEmail());

        preparedStatement.execute();
    }

    public void actualizarCliente(int id, String columna, String nuevoValor) throws SQLException {
        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?", SchemDB.TAB_CLIENTES,columna, SchemDB.COL_IDCLIENTE);
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, nuevoValor);
        preparedStatement.setInt(2, id);
        preparedStatement.execute();
    }

    public void eliminarCliente(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?", SchemDB.TAB_CLIENTES, SchemDB.COL_IDCLIENTE, id);
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }



}
