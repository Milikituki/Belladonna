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

}
