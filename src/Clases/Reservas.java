package Clases;
import com.mongodb.client.*;
import org.bson.Document;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * La clase Usuarios representa un usuario con varios atributos y métodos para
 * gestionar la validación, cifrado y operaciones CRUD con una base de datos MongoDB.
 */
public class Reservas extends Logeo{
    String horReservado, input, btn;
    int dia, mes, anio;

    /**
     * Constructor por defecto de la clase Reservas.
     */
    public Reservas() {
    }
    /**
     * Constructor con parámetros de la clase Reservas.
     *
     * @param horReservado Horario reservado.
     * @param input valor del atributo el cual se va a actualizar.
     * @param btn atributo al cual se realizará una actualización.
     * @param dia día de la reserva.
     * @param mes mes de la reserva.
     * @param anio año de la reserva.
     */

    public Reservas(String horReservado, String input, String btn, int dia, int mes, int anio) {
        this.horReservado = horReservado;
        this.input = input;
        this.btn = btn;
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

    //Métodos getter y setter de la clase Reservas.
    /**
     * Otiene el horario reservado.
     * @return horario reservado
     */
    public String getHorReservado() {
        return horReservado;
    }

    /**
     * Establece el horario reservado.
     * @param horReservado horario reservado
     */
    public void setHorReservado(String horReservado) {
        this.horReservado = horReservado;
    }

    /**
     * Obtiene el valor del atributo a actualizar.
     * @return valor del atributo a actualizar
     */
    public String getInput() {
        return input;
    }

    /**
     * Establece el valor del atributo a actualizar.
     * @param input valor del atributo a actualizar
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Obtiene el atributo a actualizar.
     * @return atributo a actualizar
     */
    public String getBtn() {
        return btn;
    }

    /**
     * Establece el atributo a actualizar.
     * @param btn atributo a actualizar
     */
    public void setBtn(String btn) {
        this.btn = btn;
    }

    /**
     * Obtiene el día de la reserva.
     * @return día de la reserva
     */
    public int getDia() {
        return dia;
    }

    /**
     * Establece el día de la reserva.
     * @param dia día de la reserva
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Obtiene el mes de la reserva.
     * @return mes de la reserva
     */
    public int getMes() {
        return mes;
    }

    /**
     * Establece el mes de la reserva.
     * @param mes mes de la reserva
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Obtiene el año de la reserva.
     * @return año de la reserva
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Establece el año de la reserva.
     * @param anio año de la reserva
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    //Numero de reserva
    /**
     * Obtiene el número siguiente al número mayor almacenado en mongo.
     * @return número de reserva
     */
    public int numReserva() {
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            // Encontrar el número mayor de reserva, con la función sort, tomando con el -1 el número mayor
            // .first() devuelve el primer documento que cumple con la condición
            Document numAlto = col.find().sort(new Document("id", -1)).first();
            // Si no hay reservas, el número de reserva es 1
            int numAlto2 = 0;
            // Si hay reservas, el número de reserva es el número mayor + 1
            if (numAlto != null) {
                // Obtener el número de reserva
                numAlto2 = numAlto.getInteger("id");
            }
            // Devolver el número de reserva
            return numAlto2 + 1;
        }
    }

    //Mostrar tabla de reservas
    /**
     * Muestra las reservas para los Usuarios en una tabla.
     * @param tabla tabla donde se mostrarán las reservas
     */
    public void mostrarReservas(JTable tabla) {
        // Crear modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        // Establecer propiedades de la tabla
        tabla.setRowHeight(25);
        // Establecer colores de la tabla
        tabla.setBackground(new Color(35,35,35));
        // Establecer color de la letra de la tabla
        tabla.setForeground(new Color(255,255,255));
        // Limpiar la tabla
        model.setRowCount(0);
        // Establecer encabezados de la tabla
        model.setColumnIdentifiers(new Object[]{"ID Reserva", "Nombre Cancha",
                "Codigo Cancha", "Horario Reservado", "Fecha Reservada"});

        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            // Crear un filtro para buscar las reservas de la cancha
            Document filter = new Document("nombreCancha", Logeo.getNombreCancha()).append("fecReservada", Logeo.getFecha());
            // Buscar las reservas de la cancha
            FindIterable<Document> iter = col.find(filter);
            for (Document doc : iter) {
                // Obtener datos de la Base de Datos
                int idRes = doc.getInteger("id");
                String nom = doc.getString("nombreCancha");
                String cod = doc.getString("codigo");
                String horRes = doc.getString("horReservado");
                String fecRes = doc.getString("fecReservada");
                // Agregar los datos a la tabla
                model.addRow(new Object[]{String.valueOf(idRes), nom, cod, horRes, fecRes});
            }
        }
    }

    //Verificar si se selecciono un registro
    /**
     * Verifica si se ha seleccionado un registro en la tabla.
     * @param box caja de opciones de los horarios.
     * @return true si se ha seleccionado un registro, false en caso contrario
     */
    public boolean seleccionarRegistro(JComboBox box) {
        return box.getSelectedIndex() != 0;
    }

    //Tomar valor del JComboBox
    /**
     * Toma el valor seleccionado en el JComboBox.
     * @param box caja de opciones de los horarios.
     */
    public void tomarValor(JComboBox box) {
            setHorReservado(box.getSelectedItem().toString());
            }

    //verificar horario disponible
    /**
     * Verifica si el horario seleccionado está disponible, tomando en cuenta la fecha y el horario.
     * @return true si el horario está disponible, false en caso contrario
     */
    public boolean verificarHorario() {
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            // Crear un documento con los datos de la reserva
            Document doc = new Document("horReservado", getHorReservado()).append("fecReservada", Logeo.getFecha());
            // Buscar si hay una reserva para esa fecha y horario
            FindIterable<Document> iter = col.find(doc);
            // Verificar si hay una reserva para ese horario
            for (Document document : iter) {
                // Obtener datos de la Base de Datos
                String hor = document.getString("horReservado");
                String fec = document.getString("fecReservada");
                String cod = document.getString("codigo");
                // Verificar si el horario está reservado
                if (hor.equals(getHorReservado()) && fec.equals(Logeo.getFecha()) && cod.equals(Logeo.getCodigo())) {
                    return false;
                }
            }
            return true;
        }
    }


    /**
     * Verifica si la fecha seleccionada no se encuentra ya reservada, tomando en cuenta la fecha, el horario y el nombre de la cancha.
     * @param fecha fecha seleccionada
     * @param horario horario seleccionado
     * @return true si la fecha es posterior a la fecha actual, false en caso contrario
     */
    public boolean verificarDisponibilidad(String fecha, String horario) {
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            // Crear un documento con los datos de la reserva
            Document doc = new Document("fecReservada", fecha)
                    .append("horReservado", horario)
                    .append("nombreCancha", Logeo.getNombreCancha());
            // Buscar si hay una reserva para esa fecha y horario
            FindIterable<Document> iter = col.find(doc);
            return iter.first() == null; // Devuelve true si no hay reserva para esa fecha y horario
        }
    }

    //Reservar cancha
    /**
     * Reserva una cancha, almacenando los datos en la base de datos.
     * @param lbl etiqueta donde se mostrará el mensaje de reserva exitosa
     */
    public void reservarCancha(JLabel lbl) {
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            // Crear un documento con los datos de la reserva
            Document docu = new Document("id", numReserva())
                    .append("nombre", Logeo.getNombre())
                    .append("cedula", Logeo.getCedula())
                    .append("nombreCancha", Logeo.getNombreCancha())
                    .append("codigo", Logeo.getCodigo())
                    .append("horReservado", getHorReservado())
                    .append("fecReservada", Logeo.getFecha());
            // Insertar la reserva en la base de datos
            col.insertOne(docu);
            lbl.setText("Reserva exitosa");
        }
    }

    //Mostrar reservas al administrador y al dueño
    /**
     * Muestra las reservas para el dueño y administrador de la cancha en una tabla.
     * @param tabla tabla donde se mostrarán las reservas
     */
    public void mostrarReservasDuenio(JTable tabla) {
        // Crear modelo de la tabla
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        // Establecer propiedades de la tabla
        tabla.setRowHeight(25);
        // Establecer colores de la tabla
        tabla.setBackground(new Color(35,35,35));
        // Establecer color de la letra de la tabla
        tabla.setForeground(new Color(255,255,255));
        // Limpiar la tabla
        model.setRowCount(0);
        // Establecer encabezados de la tabla
        model.setColumnIdentifiers(new Object[]{"ID Reserva", "Nombre Responsable", "Cédula","Nombre Cancha",
                "Codigo Cancha", "Horario Reservado", "Fecha Reservada"});

        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            FindIterable<Document> iter = col.find();
            // Mostrar todas los datos
            for (Document doc : iter) {
                // Obtener datos de la Base de Datos
                int idRes = doc.getInteger("id");
                String nom = doc.getString("nombre");
                String ced = doc.getString("cedula");
                String can = doc.getString("nombreCancha");
                String cod = doc.getString("codigo");
                String horRes = doc.getString("horReservado");
                String fecRes = doc.getString("fecReservada");
                // Agregar los datos a la tabla
                model.addRow(new Object[]{String.valueOf(idRes), nom, ced, can, cod, horRes, fecRes});
            }
        }
    }

    //Seleccionar reserva
    /**
     * Verifica si se ha seleccionado una reserva en la tabla.
     * @param table tabla donde se mostrarán las reservas
     * @return true si se ha seleccionado una reserva, false en caso contrario
     */
    public boolean seleccionarReserva(JTable table){
        return table.getSelectedRow() != -1;
    }

    //Eliminar reserva
    /**
     * Elimina una reserva de la base de datos.
     * @param codigo código de la reserva a eliminar
     */
    public void eliminarRegistro(int codigo){
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            Document doc = new Document("id", codigo);
            col.deleteOne(doc);
        }
    }

    //Actualizar reserva
    /**
     * Actualiza un registro de la base de datos.
     * @param label etiqueta donde se mostrará el mensaje de actualización exitosa
     */
    public void actualizarRegistro(JLabel label){
        label.setText("");
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");

            // Filtro para encontrar la reserva por id
            Document filter = new Document("id", Logeo.getNumReserva());

            // Documento de actualización
            Document update = new Document();
            if (btn.equals("fecReservada")) {
                update.append("fecReservada", input);
            } else if (btn.equals("horReservada")) {
                update.append("horReservado", input);
            }

            // Actualizar la reserva
            col.updateOne(filter, new Document("$set", update));

            label.setText("Registro actualizado");
            label.setVisible(true);
        } catch (Exception e) {
            label.setText("No se ha actualizado el registro: " + e.getMessage());
            label.setVisible(true);
        }
    }
}

