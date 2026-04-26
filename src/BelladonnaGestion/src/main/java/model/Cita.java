package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cita {
    private int idCita, idCliente, idServicio, idEmpleado;
    private LocalDate fecha;
    private LocalTime hora;

    public Cita(int idCliente, int idServicio, int idEmpleado, LocalDate fecha, LocalTime hora) {

        this.idCliente = idCliente;
        this.idServicio = idServicio;
        this.idEmpleado = idEmpleado;
        this.fecha = fecha;
        this.hora = hora;
    }
    @Override
    public String toString() {
        return "["
                + idCita + "] "
                + fecha + " " + hora + " | " +
                "Cliente: " + idCliente + " | " +
                "Servicio: " + idServicio + " | " +
                "Empleado: " + idEmpleado;
    }
}
