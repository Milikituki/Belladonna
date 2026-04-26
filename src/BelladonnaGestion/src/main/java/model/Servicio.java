package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Servicio {
    private int idServicio, duracion;
    private double precio;
    private String nombre;

    public Servicio(int idServicio, String nombre, int duracion, double precio) {
        this.idServicio = idServicio;
        this.duracion = duracion;
        this.precio = precio;
        this.nombre = nombre;
    }

    public Servicio(String nombre, int duracion, double precio) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.precio = precio;
    }
    @Override
    public String toString() {
        return "["
                + idServicio + "] "
                + nombre + " | " +
                "Precio: " + precio + "€ | " +
                "Duración: " + duracion + " min";
    }
}

