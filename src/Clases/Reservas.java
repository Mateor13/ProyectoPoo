package Clases;
import com.mongodb.client.*;
import org.bson.Document;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Reservas extends Logeo{
    String horReservado;

    public Reservas() {
    }

    public Reservas(String fecha, String cancha, String cedula, String codigo, String nombre, String horReservado, int num) {
        this.horReservado = horReservado;
    }

    public String getHorReservado() {
        return horReservado;
    }

    public void setHorReservado(String horReservado) {
        this.horReservado = horReservado;
    }

    //Numero de reserva
    public int numReserva() {
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            Document numAlto = col.find().sort(new Document("id", -1)).first();
            int numAlto2 = 0;
            if (numAlto != null) {
                numAlto2 = numAlto.getInteger("id");
            }
            return numAlto2 + 1;
        }
    }

    //Mostrar tabla de reservas
    public void mostrarReservas(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.setRowHeight(25);
        tabla.setBackground(new Color(35,35,35));
        tabla.setForeground(new Color(255,255,255));
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"ID Reserva", "Nombre Cancha",
                "Codigo Cancha", "Horario Reservado", "Fecha Reservada"});

        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            Document filter = new Document("nombreCancha", Logeo.getNombreCancha()).append("fecReservada", Logeo.getFecha());
            FindIterable<Document> iter = col.find(filter);
            for (Document doc : iter) {
                int idRes = doc.getInteger("id");
                String nom = doc.getString("nombreCancha");
                String cod = doc.getString("codigo");
                String horRes = doc.getString("horReservado");
                String fecRes = doc.getString("fecReservada");
                model.addRow(new Object[]{String.valueOf(idRes), nom, cod, horRes, fecRes});
            }
        }
    }

    //Verificar si se selecciono un registro
    public boolean seleccionarRegistro(JComboBox box) {
        return box.getSelectedIndex() != 0;
    }
    //Tomar valor del JComboBox
    public void tomarValor(JComboBox box) {
            setHorReservado(box.getSelectedItem().toString());
            }

    //verificar horario disponible
    public boolean verificarHorario() {
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            Document doc = new Document("horReservado", getHorReservado()).append("fecReservada", Logeo.getFecha());
            FindIterable<Document> iter = col.find(doc);
            for (Document document : iter) {
                String hor = document.getString("horReservado");
                String fec = document.getString("fecReservada");
                String cod = document.getString("codigo");
                if (hor.equals(getHorReservado()) && fec.equals(Logeo.getFecha()) && cod.equals(Logeo.getCodigo())) {
                    return false;
                }
            }
            return true;
        }
    }

    //Reservar cancha
    public void reservarCancha(JLabel lbl) {
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            Document docu = new Document("id", numReserva())
                    .append("nombre", Logeo.getNombre())
                    .append("cedula", Logeo.getCedula())
                    .append("nombreCancha", Logeo.getNombreCancha())
                    .append("codigo", Logeo.getCodigo())
                    .append("horReservado", getHorReservado())
                    .append("fecReservada", Logeo.getFecha());
            col.insertOne(docu);
            lbl.setText("Reserva exitosa");
        }
    }

    public void mostrarReservasDuenio(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.setRowHeight(25);
        tabla.setBackground(new Color(35,35,35));
        tabla.setForeground(new Color(255,255,255));
        model.setRowCount(0);
        model.setColumnIdentifiers(new Object[]{"ID Reserva", "Nombre Responsable", "Cádula","Nombre Cancha",
                "Codigo Cancha", "Horario Reservado", "Fecha Reservada"});

        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            FindIterable<Document> iter = col.find();
            for (Document doc : iter) {
                int idRes = doc.getInteger("id");
                String nom = doc.getString("nombre");
                String ced = doc.getString("cedula");
                String can = doc.getString("nombreCancha");
                String cod = doc.getString("codigo");
                String horRes = doc.getString("horReservado");
                String fecRes = doc.getString("fecReservada");
                model.addRow(new Object[]{String.valueOf(idRes), nom, ced, can, cod, horRes, fecRes});
            }
        }
    }

    public boolean seleccionarReserva(JTable table){
        return table.getSelectedRow() != -1;
    }

    public void eliminarRegistro(int codigo){
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Reservas");
            Document doc = new Document("id", codigo);
            col.deleteOne(doc);
        }
    }

}

