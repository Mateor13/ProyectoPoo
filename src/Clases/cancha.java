package Clases;

import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cancha {
    String numero, nombre, ubicacion, numeroJugadores, btn, input;
    List<String> lines = List.of();

    public cancha() {
    }

    public cancha(String numero, String nombre, String ubicacion, String numeroJugadores, String btn, String input, List<String> lines) {
        this.numero = numero;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.numeroJugadores = numeroJugadores;
        this.lines = lines;
        this.btn = btn;
        this.input = input;
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

    public String getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(String numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public boolean vernumCan() {
        Pattern patron = Pattern.compile("^\\d{5}$");
        Matcher matcher = patron.matcher(getNumero());
        return matcher.matches();
    }

    public boolean seleccionarRegitro(JTable table1) {
        if (table1.getSelectedRow() == -1) {
            return false;
        }
        return true;
    }

    public void mostrarCanchas(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.setRowHeight(25);
        tabla.setBackground(new java.awt.Color(35,35,35));
        tabla.setForeground(new java.awt.Color(255,255,255));
        model.setRowCount(0);
        model.addColumn("Número Cancha");
        model.addColumn("Nombre");
        model.addColumn("Dirección");
        model.addColumn("Número jugadores");
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Canchas");
            FindIterable<Document> iter = col.find();
            for (Document doc : iter) {
                String NumCancha = (String) doc.get("id");
                String nom = doc.getString("nombre");
                String dir = doc.getString("direccion");
                String numJug = doc.getString("numJugadores");
                model.addRow(new Object[]{NumCancha, nom, dir, numJug});
            }
        }
    }

    public void eliminarRegistro(String codigo) {
            try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                MongoDatabase db = mongo.getDatabase("futbolito");
                MongoCollection<Document> col = db.getCollection("Canchas");
                Document filtro = new Document("id", codigo);
                col.deleteOne(filtro);
        }
    }
    public void actualizarRegistro(JLabel label) {
        label.setText("");
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Canchas");
            Document filter = new Document("id", Logeo.getCodigo());
            Document update = new Document("$set", new Document(getBtn(), getInput()));
            col.updateOne(filter, update);
            label.setText("Registro actualizado");
            label.setVisible(true);
        } catch (Exception e) {
            label.setText("No se ha actualizado el registro: " + e.getMessage());
            label.setVisible(true);
        }
    }
}
