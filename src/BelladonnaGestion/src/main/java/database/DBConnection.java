package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection conexion;

    public static Connection getConexion() throws SQLException {
        if (conexion == null) {
            createConnection();
        }
        return conexion;
    }

    private static void createConnection() throws SQLException {
        String user = "root";
        String pass = "";
        String database = "Belladonna";

        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + database, user, pass);

    }
}
