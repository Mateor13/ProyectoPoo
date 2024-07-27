package Clases;

import java.util.List;

public class cancha {
    String numero, nombre, ubicacion;
    List<String> lines = List.of();
    public cancha() {
    }

    public cancha(String numero, String nombre, String ubicacion, List<String> lines) {
        this.numero = numero;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.lines = lines;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<String> getLines() {
        return lines;
    }

    public void setLines(List<String> lines) {
        this.lines = lines;
    }
}
