package database;

public interface SchemDB {

    String TAB_CLIENTES = "clientes";
    String COL_IDCLIENTE ="id_cliente";
    String COL_NOMBRE = "nombre";
    String COL_TELEFONO = "telefono";
    String COL_EMAIL = "email";

    String TAB_CITAS = "citas";
    String COL_IDCITA ="id_cita";
    String COL_FECHA = "fecha";
    String COL_HORA = "hora";

    String TAB_EMPLEADOS = "empleados";
    String COL_IDEMPLEADO ="id_empleado";
    String COL_ESPECIALIDAD = "especialidad";

    String TAB_SERVICIOS = "servicios";
    String COL_IDSERVICIO ="id_servicio";
    String COL_PRECIO = "precio";
    String COL_DURACION = "duracion";

    String TAB_SERVICIO_EMPLEADO = "servicio_empleado";


}
