package dao;

import database.DBConnection;
import database.SchemDB;
import model.Cita;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CitaDAO {
    private Connection conexion;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    public CitaDAO() {
        try {
            conexion = DBConnection.getConexion();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Cita> obtenerCitas() {
        System.out.println("Iniciando obtencion de citas...");
        List<Cita> citas = new ArrayList<>();
        String query = "SELECT * FROM " + SchemDB.TAB_CITAS;
        try{
            preparedStatement = conexion.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(SchemDB.COL_IDCITA);
                int idCliente = resultSet.getInt(SchemDB.COL_IDCLIENTE);
                int idServicio = resultSet.getInt(SchemDB.COL_IDSERVICIO);
                int idEmpleado = resultSet.getInt(SchemDB.COL_IDEMPLEADO);
                LocalDate fecha = resultSet.getDate(SchemDB.COL_FECHA).toLocalDate();
                LocalTime hora = resultSet.getTime(SchemDB.COL_HORA).toLocalTime();

                citas.add(new Cita(id, idCliente, idServicio, idEmpleado, fecha, hora));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return citas;
    }

    public void insertarCita(Cita cita) throws SQLException {
        String query = String.format("INSERT INTO %s (%s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?)", SchemDB.TAB_CITAS, SchemDB.COL_IDCLIENTE, SchemDB.COL_IDSERVICIO, SchemDB.COL_IDEMPLEADO, SchemDB.COL_FECHA, SchemDB.COL_HORA);
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, cita.getIdCliente());
        preparedStatement.setInt(2, cita.getIdServicio());
        preparedStatement.setInt(3, cita.getIdEmpleado());
        preparedStatement.setDate(4, Date.valueOf(cita.getFecha()));
        preparedStatement.setTime(5, Time.valueOf(cita.getHora()));

        preparedStatement.execute();
    }

    public void actualizarCita(int id, String columna, String nuevoValor) throws SQLException {
        String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?", SchemDB.TAB_CITAS,columna, SchemDB.COL_IDCITA);
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setString(1, nuevoValor);
        preparedStatement.setInt(2, id);
        preparedStatement.execute();
    }

    public void eliminarCita(int id) throws SQLException {
        String query = String.format("DELETE FROM %s WHERE %s = ?", SchemDB.TAB_CITAS, SchemDB.COL_IDCITA);
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public List<Cita> verCitasHoy() throws SQLException {
        List<Cita> citas = new ArrayList<>();
        String query = String.format("SELECT * FROM %s WHERE %s = ?", SchemDB.TAB_CITAS, SchemDB.COL_FECHA);
        preparedStatement = conexion.prepareStatement(query);
        preparedStatement.setDate(1, Date.valueOf(LocalDate.now()));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt(SchemDB.COL_IDCITA);
            int idCliente = resultSet.getInt(SchemDB.COL_IDCLIENTE);
            int idServicio = resultSet.getInt(SchemDB.COL_IDSERVICIO);
            int idEmpleado = resultSet.getInt(SchemDB.COL_IDEMPLEADO);
            LocalDate fecha = resultSet.getDate(SchemDB.COL_FECHA).toLocalDate();
            LocalTime hora = resultSet.getTime(SchemDB.COL_HORA).toLocalTime();
            citas.add(new Cita(id, idCliente, idServicio, idEmpleado, fecha, hora));
        }
        return citas;
    }

    public List<String> obtenerCitasDetalladas(LocalDate fecha) throws SQLException {
        List<String> citas = new ArrayList<>();
        String query = String.format(
                "SELECT c.%s, c.%s, c.%s, c.%s, cl.%s AS cliente, s.%s AS servicio, e.%s AS empleado " +
                        "FROM %s c " +
                        "INNER JOIN %s cl ON c.%s = cl.%s " +
                        "INNER JOIN %s s ON c.%s = s.%s " +
                        "INNER JOIN %s e ON c.%s = e.%s",
                SchemDB.COL_IDCITA, SchemDB.COL_FECHA, SchemDB.COL_HORA, SchemDB.COL_IDCLIENTE,
                SchemDB.COL_NOMBRE, SchemDB.COL_NOMBRE, SchemDB.COL_NOMBRE,
                SchemDB.TAB_CITAS,
                SchemDB.TAB_CLIENTES, SchemDB.COL_IDCLIENTE, SchemDB.COL_IDCLIENTE,
                SchemDB.TAB_SERVICIOS, SchemDB.COL_IDSERVICIO, SchemDB.COL_IDSERVICIO,
                SchemDB.TAB_EMPLEADOS, SchemDB.COL_IDEMPLEADO, SchemDB.COL_IDEMPLEADO
        );
        if (fecha != null) {
            query += String.format(" WHERE c.%s = ?", SchemDB.COL_FECHA);
        }

        preparedStatement = conexion.prepareStatement(query);
        if (fecha != null) {
            preparedStatement.setDate(1, Date.valueOf(fecha));
        }
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int idCita = resultSet.getInt(SchemDB.COL_IDCITA);
            int idCliente = resultSet.getInt(SchemDB.COL_IDCLIENTE);
            String fechaCita = resultSet.getString(SchemDB.COL_FECHA);
            String horaCita = resultSet.getString(SchemDB.COL_HORA);
            String cliente = resultSet.getString("cliente");
            String servicio = resultSet.getString("servicio");
            String empleado = resultSet.getString("empleado");
            citas.add("[" + idCita + "] " + fechaCita + " " + horaCita + " | Cliente (" + idCliente + "): " + cliente + " | Servicio: " + servicio + " | Empleado: " + empleado);
        }
        return citas;
    }
}
