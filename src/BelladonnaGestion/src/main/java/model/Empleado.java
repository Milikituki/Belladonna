package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Empleado {
    private int idEmpleado;
    private String nombre, especialidad;

    public Empleado(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
    @Override
    public String toString() {
        return "["
                + idEmpleado + "] "
                + nombre + " | " +
                "Especialidad: " + especialidad;
    }
}
