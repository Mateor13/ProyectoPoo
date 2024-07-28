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
    String numero, nombre, ubicacion, numeroJugadores;
    List<String> lines = List.of();
    public cancha() {
    }

    public cancha(String numero, String nombre, String ubicacion, String numeroJugadores, List<String> lines) {
        this.numero = numero;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.numeroJugadores = numeroJugadores;
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

    public String getNumeroJugadores() {
        return numeroJugadores;
    }

    public void setNumeroJugadores(String numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    public boolean vernumCan(){
        Pattern patron = Pattern.compile("^\\d{5}$");
        Matcher matcher = patron.matcher(getNumero());
        return matcher.matches();
    }

    public void mostrarCanchas(JTable tabla){
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.addColumn("Numero Cancha");
        model.addColumn("Nombre");
        model.addColumn("Dirección");
        model.addColumn("Número jugadores");
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
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

    public void eliminarRegistro(JTable tabla, JLabel label){
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        if (tabla.getSelectedRow()==-1){
            label.setText("No se ha seleccionado el registro");
        }else{
            label.setText("");
            try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
                MongoDatabase db = mongo.getDatabase("futbolito");
                MongoCollection<Document> col = db.getCollection("Canchas");

                String codigo = (String) model.getValueAt(tabla.getSelectedRow(), 0).toString();
                Document filter = new Document("id", codigo);
                DeleteResult res = col.deleteOne(filter);

                model.removeRow(tabla.getSelectedRow());
                label.setText("Registro eliminado");
            } catch (Exception e) {
                label.setText("No se ha eliminado el registro: " + e.getMessage());
            }
        }
    }
}
