package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cliente {
    private int idCliente;
    private String nombre, telefono, email;

    public Cliente(String nombre, String telefono, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = correo;
    }

    @Override
    public String toString() {
        return "["
                + idCliente + "] "
                + nombre + " | " +
                "Tel: " + telefono + " | " +
                "Correo: " + email + '\'';
    }
}

