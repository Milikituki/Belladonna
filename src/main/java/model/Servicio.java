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
}
