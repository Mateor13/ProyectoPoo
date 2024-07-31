package Clases;
import com.mongodb.client.*;
import org.bson.Document;
import javax.swing.*;

public class Reservas extends Logeo{
    String fecha, cancha, cedula, codigo, nombre;

    public Reservas() {
    }

    public Reservas(String hora, String fecha, String cancha, String cedula, String codigo, String nombre) {
        this.fecha = Logeo.getFecha();
        this.cancha = Logeo.getNombreCancha();
        this.cedula = Logeo.getCedula();
        this.codigo = Logeo.getCodigo();
        this.nombre = Logeo.getNombre();
    }
    //Mostrar tabla de reservas
}
