package Clases;

/**
 * Clase que se encarga de almacenar los datos del usuario que se logea en el sistema.
 * Atributos:
 * - nombre: String
 * - cedula: String
 * - codigo: String
 * - fecha: String
 * - nombreCancha: String
 * - horario: String
 * - numReserva: int
 * Métodos:
 * - Getters y Setters de los atributos.
 */
public class Logeo {
    /**
     * Atributos de la clase.
     */
    private static String nombre, cedula, codigo, fecha, nombreCancha, horario;
    private static int numReserva;

    /**
     * Establece el nombre del usuario.
     * @return nombre del usuario.
     */
    public static String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el nombre del usuario.
     * @param nombre nombre del usuario.
     */
    public static void setNombre(String nombre) {
        Logeo.nombre = nombre;
    }

    /**
     * Obtiene la cédula del usuario.
     * @return cédula del usuario.
     */
    public static String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula del usuario.
     * @param cedula cédula del usuario.
     */
    public static void setCedula(String cedula) {
        Logeo.cedula = cedula;
    }

    /**
     * Obtiene el código de la reserva.
     * @return código de la reserva.
     */
    public static String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de la reserva.
     * @param codigo código de la reserva.
     */
    public static void setCodigo(String codigo) {
        Logeo.codigo = codigo;
    }

    /**
     * Obtiene la fecha de la reserva.
     * @return fecha de la reserva.
     */
    public static String getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de la reserva.
     * @param fecha fecha de la reserva.
     */
    public static void setFecha(String fecha) {
        Logeo.fecha = fecha;
    }

    /**
     * Obtiene el nombre de la cancha.
     * @return nombre de la cancha.
     */
    public static String getNombreCancha() {
        return nombreCancha;
    }

    /**
     * Establece el nombre de la cancha.
     * @param nombreCancha nombre de la cancha.
     */
    public static void setNombreCancha(String nombreCancha) {
        Logeo.nombreCancha = nombreCancha;
    }

    /**
     * Obtiene el número de la reserva.
     * @return número de la reserva.
     */
    public static int getNumReserva() {
        return numReserva;
    }

    /**
     * Establece el número de la reserva.
     * @param numReserva número de la reserva.
     */
    public static void setNumReserva(int numReserva) {
        Logeo.numReserva = numReserva;
    }

    /**
     * Obtiene el horario de la reserva.
     * @return horario de la reserva.
     */
    public static String getHorario() {
        return horario;
    }

    /**
     * Establece el horario de la reserva.
     * @param horario horario de la reserva.
     */
    public static void setHorario(String horario) {
        Logeo.horario = horario;
    }
}
