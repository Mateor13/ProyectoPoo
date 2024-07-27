package Clases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Usuarios {
    String nombre, apellido, email, telefono, clave, cedula, claveconf, encripclave;
    int diaNacimiento, mesNacimiento, anoNacimiento;
       //Constructores
    public Usuarios() {
    }

    public Usuarios(String nombre, String apellido, String email, String telefono, String clave, String cedula, String claveconf, String encripclave, int diaNacimiento, int mesNacimiento, int anoNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.clave = clave;
        this.cedula = cedula;
        this.claveconf = claveconf;
        this.encripclave = encripclave;
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

    public boolean verCorreo() {
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = patron.matcher(getEmail());
        return matcher.find();
    }
    public boolean verNume() {
        Pattern patron = Pattern.compile("^09\\d{8}$");
        Matcher tel = patron.matcher(getTelefono());
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
}