package Clases;

import Forms.fecha;
import com.mongodb.client.*;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Usuarios {
    String nombre, apellido, email, telefono, clave, cedula, claveconf, encripclave, inp, btn;
    int diaNacimiento, mesNacimiento, anoNacimiento;
       //Constructores
    public Usuarios() {
    }

    public Usuarios(String nombre, String apellido, String email, String telefono, String clave, String cedula, String claveconf, String encripclave, String inp, String btn, int diaNacimiento, int mesNacimiento, int anoNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.clave = clave;
        this.cedula = cedula;
        this.claveconf = claveconf;
        this.encripclave = encripclave;
        this.inp = inp;
        this.btn = btn;
        this.diaNacimiento = diaNacimiento;
        this.mesNacimiento = mesNacimiento;
        this.anoNacimiento = anoNacimiento;
    }

    //getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getClaveconf() {
        return claveconf;
    }

    public void setClaveconf(String claveconf) {
        this.claveconf = claveconf;
    }

    public int getDiaNacimiento() {
        return diaNacimiento;
    }

    public void setDiaNacimiento(int diaNacimiento) {
        this.diaNacimiento = diaNacimiento;
    }

    public int getMesNacimiento() {
        return mesNacimiento;
    }

    public void setMesNacimiento(int mesNacimiento) {
        this.mesNacimiento = mesNacimiento;
    }

    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    public String getEncripclave() {
        return encripclave;
    }

    public void setEncripclave(String encripclave) {
        this.encripclave = encripclave;
    }

    public String getInp() {
        return inp;
    }

    public void setInp(String inp) {
        this.inp = inp;
    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    public boolean verCorreo() {
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = patron.matcher(getEmail());
        return matcher.matches();
    }

    public boolean verCorreo2(String text) {
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = patron.matcher(text);
        return matcher.matches();
    }

    public boolean verNume() {
        Pattern patron = Pattern.compile("^09\\d{8}$");
        Matcher tel = patron.matcher(getTelefono());
        return tel.matches();
    }

    public boolean verNume2(String text) {
        Pattern patron = Pattern.compile("^09\\d{8}$");
        Matcher tel = patron.matcher(text);
        return tel.matches();
    }

    public boolean verCedula() {
        Pattern patron = Pattern.compile("^\\d{10}$");
        Matcher ced = patron.matcher(getCedula());
        return ced.matches();
    }
    public String generateHash() {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(getClave().getBytes());
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateHash2(String clave1) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(clave1.getBytes());
            return bytesToHex(encodedhash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public void mostrarUsuarios(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        tabla.setRowHeight(25);
        model.addColumn("Cédula");
        model.addColumn("Nombre");
        model.addColumn("Apellido");
        model.addColumn("Correo");
        model.addColumn("Teléfono");
        model.addColumn("Fecha de Nacimiento");
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Usuarios");
            FindIterable<Document> iter = col.find();
            for (Document doc : iter) {
                String ci = doc.getString("cedula");
                String nom = doc.getString("nombre");
                String ape = doc.getString("apellido");
                String cor = doc.getString("correo");
                String tel = doc.getString("celular");
                String fecha = doc.getString("fechaNacimiento");
                model.addRow(new Object[]{ci, nom, ape, cor, tel, fecha});
            }
        }
    }
    public void eliminarRegistro(String cedula1) {
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                MongoDatabase db = mongo.getDatabase("futbolito");
                MongoCollection<Document> col = db.getCollection("Usuarios");
                Document filter = new Document("cedula", cedula1);
                col.deleteOne(filter);
        }
    }
    public void actualizarRegistro(JLabel label) {
        label.setText("");
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Usuarios");
            Document filter = new Document("cedula", getCedula());
            Document update = new Document("$set", new Document(getBtn(), getInp()));
            col.updateOne(filter, update);
            label.setText("Registro actualizado");
            label.setVisible(true);
        } catch (Exception e) {
            label.setText("No se ha actualizado el registro: " + e.getMessage());
            label.setVisible(true);
        }
    }
    public boolean seleccionarRegitro(JTable table1) {
        if (table1.getSelectedRow() == -1) {
            return false;
        }
        return true;
    }

    public boolean verClave(String txt, JLabel lbl){
        String encodcl = generateHash2(txt);
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Usuarios");
            Document doc = new Document("cedula", getCedula());
            FindIterable<Document> iterable = col.find(doc);
            for (Document document : iterable){
                String clave = document.getString("clave");
                if (encodcl.equals(clave)){
                    lbl.setText("");
                    return true;
                }
            }
            lbl.setText("Clave incorrecta");
            return false;
        }
    }
}