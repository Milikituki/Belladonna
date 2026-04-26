package dao;

import database.DBConnection;
import database.SchemDB;
import model.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {

        private Connection conexion;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        public EmpleadoDAO() {
            try {
                conexion = DBConnection.getConexion();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public List<Empleado> obtenerEmpleados() {
            System.out.println("Iniciando obtencion de empleados...");
            List<Empleado> empleados = new ArrayList<>();
            String query = "SELECT * FROM " + SchemDB.TAB_EMPLEADOS;
            try{
                preparedStatement = conexion.prepareStatement(query);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt(SchemDB.COL_IDEMPLEADO);
                    String nombre = resultSet.getString(SchemDB.COL_NOMBRE);
                    String especialidad = resultSet.getString(SchemDB.COL_ESPECIALIDAD);
                    empleados.add(new Empleado(id, nombre, especialidad));
                }
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
            return empleados;
        }

        public void insertarEmpleado(Empleado empleado) throws SQLException {
            String query = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)", SchemDB.TAB_EMPLEADOS, SchemDB.COL_NOMBRE, SchemDB.COL_ESPECIALIDAD);
            preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, empleado.getNombre());
            preparedStatement.setString(2, empleado.getEspecialidad());

            preparedStatement.execute();
        }

        public void actualizarEmpleado(int id, String columna, String nuevoValor) throws SQLException {
            String query = String.format("UPDATE %s SET %s = ? WHERE %s = ?", SchemDB.TAB_EMPLEADOS,columna, SchemDB.COL_IDEMPLEADO);
            preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setString(1, nuevoValor);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();
        }

        public void eliminarEmpleado(int id) throws SQLException {
            String query = String.format("DELETE FROM %s WHERE %s = ?", SchemDB.TAB_EMPLEADOS,SchemDB.COL_IDEMPLEADO,id);
            preparedStatement = conexion.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        }
    }


