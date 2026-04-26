package dao;

import database.DBConnection;
import database.SchemDB;
import model.Servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServicioDAO {
    private Connection conexion;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public ServicioDAO() {
        try {
            conexion = DBConnection.getConexion();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Servicio> obtenerServicios() {
        System.out.println("Iniciando obtencion de servicios...");
        List<Servicio> servicios = new ArrayList<>();
        String query = "SELECT * FROM " + SchemDB.TAB_SERVICIOS;
        try{
            preparedStatement = conexion.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(SchemDB.COL_IDSERVICIO);
                String nombre = resultSet.getString(SchemDB.COL_NOMBRE);
                int duracion = resultSet.getInt(SchemDB.COL_DURACION);
                double precio = resultSet.getDouble(SchemDB.COL_PRECIO);
                servicios.add(new Servicio(id, nombre, duracion, precio));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return servicios;
    }

    public void insertarServicio(Servicio servicio) throws SQLException {
        String query = String.format("INSERT INTO %s (%s, %s, %s) VALUES (?, ?, ?)", SchemDB.TAB_SERVICIOS, SchemDB.COL_NOMBRE, SchemDB.COL_DURACION, SchemDB.COL_PRECIO);
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, servicio.getNombre());
        preparedStatement.setInt(2, servicio.getDuracion());
        preparedStatement.setDouble(3, servicio.getPrecio());
        preparedStatement.execute();
    }

    public void actualizarServicio(int id, String columna, String nuevoValor) throws SQLException {
        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?", SchemDB.TAB_SERVICIOS,columna, SchemDB.COL_IDSERVICIO);
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, nuevoValor);
        preparedStatement.setInt(2, id);
        preparedStatement.execute();
    }

    public void eliminarServicio(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?", SchemDB.TAB_SERVICIOS, SchemDB.COL_IDSERVICIO);
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }
}
