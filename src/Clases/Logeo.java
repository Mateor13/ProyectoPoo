package Clases;

public class Logeo {
    private static String nombre, cedula, codigo, fecha, nombreCancha, horario;
    private static int numReserva;

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Logeo.nombre = nombre;
    }

    public static String getCedula() {
        return cedula;
    }

    public static void setCedula(String cedula) {
        Logeo.cedula = cedula;
    }

    public static String getCodigo() {
        return codigo;
    }

    public static void setCodigo(String codigo) {
        Logeo.codigo = codigo;
    }

    public static String getFecha() {
        return fecha;
    }

    public static void setFecha(String fecha) {
        Logeo.fecha = fecha;
    }

    public static String getNombreCancha() {
        return nombreCancha;
    }

    public static void setNombreCancha(String nombreCancha) {
        Logeo.nombreCancha = nombreCancha;
    }

    public static int getNumReserva() {
        return numReserva;
    }

    public static void setNumReserva(int numReserva) {
        Logeo.numReserva = numReserva;
    }

    public static String getHorario() {
        return horario;
    }

    public static void setHorario(String horario) {
        Logeo.horario = horario;
    }
}
