package Clases;

import com.mongodb.client.*;
import org.bson.Document;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * La clase Usuarios representa un usuario con varios atributos y métodos para
 * gestionar la validación, cifrado y operaciones CRUD con una base de datos MongoDB.
 */
public class Usuarios {
    // Atributos
    String nombre, apellido, email, telefono, clave, cedula, claveconf, encripclave, inp, btn;
    int diaNacimiento, mesNacimiento, anoNacimiento;

    /**
     * Constructor por defecto.
     */
    public Usuarios() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param nombre          El nombre del usuario.
     * @param apellido        El apellido del usuario.
     * @param email           El email del usuario.
     * @param telefono        El teléfono del usuario.
     * @param clave           La clave del usuario.
     * @param cedula          La cédula del usuario.
     * @param claveconf       Confirmación de la clave del usuario.
     * @param encripclave     La clave encriptada del usuario.
     * @param inp             Un valor de entrada específico.
     * @param btn             Un valor de botón específico.
     * @param diaNacimiento   El día de nacimiento del usuario.
     * @param mesNacimiento   El mes de nacimiento del usuario.
     * @param anoNacimiento   El año de nacimiento del usuario.
     */
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

    // Métodos getters y setters

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del usuario.
     *
     * @return El apellido del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     *
     * @param apellido El apellido del usuario.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el email del usuario.
     *
     * @return El email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     *
     * @param email El email del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene el teléfono del usuario.
     *
     * @return El teléfono del usuario.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del usuario.
     *
     * @param telefono El teléfono del usuario.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene la clave del usuario.
     *
     * @return La clave del usuario.
     */
    public String getClave() {
        return clave;
    }

    /**
     * Establece la clave del usuario.
     *
     * @param clave La clave del usuario.
     */
    public void setClave(String clave) {
        this.clave = clave;
    }

    /**
     * Obtiene la cédula del usuario.
     *
     * @return La cédula del usuario.
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * Establece la cédula del usuario.
     *
     * @param cedula La cédula del usuario.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * Obtiene la confirmación de la clave del usuario.
     *
     * @return La confirmación de la clave del usuario.
     */
    public String getClaveconf() {
        return claveconf;
    }

    /**
     * Establece la confirmación de la clave del usuario.
     *
     * @param claveconf La confirmación de la clave del usuario.
     */
    public void setClaveconf(String claveconf) {
        this.claveconf = claveconf;
    }

    /**
     * Obtiene el día de nacimiento del usuario.
     *
     * @return El día de nacimiento del usuario.
     */
    public int getDiaNacimiento() {
        return diaNacimiento;
    }

    /**
     * Establece el día de nacimiento del usuario.
     *
     * @param diaNacimiento El día de nacimiento del usuario.
     */
    public void setDiaNacimiento(int diaNacimiento) {
        this.diaNacimiento = diaNacimiento;
    }

    /**
     * Obtiene el mes de nacimiento del usuario.
     *
     * @return El mes de nacimiento del usuario.
     */
    public int getMesNacimiento() {
        return mesNacimiento;
    }

    /**
     * Establece el mes de nacimiento del usuario.
     *
     * @param mesNacimiento El mes de nacimiento del usuario.
     */
    public void setMesNacimiento(int mesNacimiento) {
        this.mesNacimiento = mesNacimiento;
    }

    /**
     * Obtiene el año de nacimiento del usuario.
     *
     * @return El año de nacimiento del usuario.
     */
    public int getAnoNacimiento() {
        return anoNacimiento;
    }

    /**
     * Establece el año de nacimiento del usuario.
     *
     * @param anoNacimiento El año de nacimiento del usuario.
     */
    public void setAnoNacimiento(int anoNacimiento) {
        this.anoNacimiento = anoNacimiento;
    }

    /**
     * Obtiene la clave encriptada del usuario.
     *
     * @return La clave encriptada del usuario.
     */
    public String getEncripclave() {
        return encripclave;
    }

    /**
     * Establece la clave encriptada del usuario.
     *
     * @param encripclave La clave encriptada del usuario.
     */
    public void setEncripclave(String encripclave) {
        this.encripclave = encripclave;
    }

    /**
     * Obtiene el valor de entrada específico.
     *
     * @return El valor de entrada específico.
     */
    public String getInp() {
        return inp;
    }

    /**
     * Establece el valor de entrada específico.
     *
     * @param inp El valor de entrada específico.
     */
    public void setInp(String inp) {
        this.inp = inp;
    }

    /**
     * Obtiene el valor de botón específico.
     *
     * @return El valor de botón específico.
     */
    public String getBtn() {
        return btn;
    }

    /**
     * Establece el valor de botón específico.
     *
     * @param btn El valor de botón específico.
     */
    public void setBtn(String btn) {
        this.btn = btn;
    }

    /**
     * Verifica si el correo electrónico del usuario tiene un formato válido.
     *
     * @return true si el correo es válido, false de lo contrario.
     */
    public boolean verCorreo() {
        // Define el patrón de un correo electrónico
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        // Verifica si el correo coincide con el patrón
        Matcher matcher = patron.matcher(getEmail());
        return !matcher.matches();
    }

    /**
     * Verifica si el texto dado es un correo electrónico válido.
     *
     * @param text El texto a verificar.
     * @return true si el texto es un correo válido, false de lo contrario.
     */
    public boolean verCorreo2(String text) {
        // Define el patrón de un correo electrónico
        Pattern patron = Pattern.compile("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        // Verifica si el correo coincide con el patrón
        Matcher matcher = patron.matcher(text);
        return matcher.matches();
    }

    /**
     * Verifica si el teléfono del usuario tiene un formato válido.
     *
     * @return true si el teléfono es válido, false de lo contrario.
     */
    public boolean verNume() {
        // Verifica si el teléfono tiene 10 dígitos y comienza con 09
        Pattern patron = Pattern.compile("^09\\d{8}$");
        // Verifica si el teléfono coincide con el patrón
        Matcher tel = patron.matcher(getTelefono());
        return !tel.matches();
    }

    /**
     * Verifica si el texto dado es un número de teléfono válido.
     *
     * @param text El texto a verificar.
     * @return true si el texto es un número de teléfono válido, false de lo contrario.
     */
    public boolean verNume2(String text) {
        // Verifica si el teléfono tiene 10 dígitos y comienza con 09
        Pattern patron = Pattern.compile("^09\\d{8}$");
        // Verifica si el teléfono coincide con el patrón
        Matcher tel = patron.matcher(text);
        return tel.matches();
    }

    /**
     * Verifica si la cédula del usuario tiene un formato válido.
     *
     * @return true si la cédula es válida, false de lo contrario.
     */
    public boolean verCedula() {
        // Verifica si la cédula tiene 10 dígitos
        Pattern patron = Pattern.compile("^\\d{10}$");
        // Verifica si la cédula coincide con el patrón
        Matcher ced = patron.matcher(getCedula());
        return !ced.matches();
    }

    /**
     * Genera un hash SHA-256 de la clave del usuario.
     *
     * @return El hash SHA-256 de la clave.
     */
    public String generateHash() {
        // Crea un objeto MessageDigest para el algoritmo SHA-256
        try {
            // Obtiene el algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Obtiene el hash de la clave
            byte[] encodedhash = digest.digest(getClave().getBytes());
            // Convierte el hash en una representación hexadecimal
            return bytesToHex(encodedhash);
            // Maneja la excepción NoSuchAlgorithmException
            // es una excepción en Java que se lanza cuando una instancia de un
            // algoritmo criptográfico solicitado no está disponible en el entorno.
        } catch (NoSuchAlgorithmException e) {
            // Lanza una excepción RuntimeException
            // errores que ocurren durante la ejecución del programa.
            throw new RuntimeException(e);
        }
    }

    /**
     * Genera un hash SHA-256 del texto de la clave dado.
     *
     * @param clave1 El texto de la clave a hashear.
     * @return El hash SHA-256 de la clave.
     */
    public String generateHash2(String clave1) {
        // Crea un objeto MessageDigest para el algoritmo SHA-256
        try {
            // Obtiene el algoritmo SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Obtiene el hash de la clave
            byte[] encodedhash = digest.digest(clave1.getBytes());
            // Convierte el hash en una representación hexadecimal
            return bytesToHex(encodedhash);
            // Maneja la excepción NoSuchAlgorithmException
        } catch (NoSuchAlgorithmException e) {
            // Lanza una excepción RuntimeException
            throw new RuntimeException(e);
        }
    }

    /**
     * Convierte un arreglo de bytes en una representación hexadecimal.
     *
     * @param hash El arreglo de bytes a convertir.
     * @return La representación hexadecimal del arreglo de bytes.
     */
    private String bytesToHex(byte[] hash) {
        // Crea un objeto StringBuilder
        StringBuilder hexString = new StringBuilder();
        // Recorre el arreglo de bytes
        for (byte b : hash) {
            // Convierte el byte en un entero sin signo y lo convierte a hexadecimal
            String hex = Integer.toHexString(0xff & b);
            // Añade un 0 al principio si el número hexadecimal es de un solo dígito
            if (hex.length() == 1) {
                // Añade el 0 al principio
                hexString.append('0');
            }
            // Añade el número hexadecimal al StringBuilder
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * Muestra los usuarios en una tabla.
     *
     * @param tabla La tabla donde se mostrarán los usuarios.
     */
    public void mostrarUsuarios(JTable tabla) {
        // Crea un modelo de tabla
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        // Establece el alto de las filas de la tabla
        tabla.setRowHeight(25);
        // Añade las columnas a la tabla
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
            // Recorre los documentos de la colección
            for (Document doc : iter) {
                String ci = doc.getString("cedula");
                String nom = doc.getString("nombre");
                String ape = doc.getString("apellido");
                String cor = doc.getString("correo");
                String tel = doc.getString("celular");
                String fecha = doc.getString("fechaNacimiento");
                // Añade los datos a la tabla
                model.addRow(new Object[]{ci, nom, ape, cor, tel, fecha});
            }
        }
    }

    /**
     * Elimina un registro de usuario por su cédula.
     *
     * @param cedula1 La cédula del usuario a eliminar.
     */
    public void eliminarRegistro(String cedula1) {
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Usuarios");
            // Crea un filtro con la cédula del usuario
            Document filtro = new Document("cedula", cedula1);
            // Elimina el registro de usuario
            col.deleteOne(filtro);
        }
    }

    /**
     * Actualiza un registro de usuario.
     *
     * @param label El JLabel donde se mostrará el mensaje de actualización.
     */
    public void actualizarRegistro(JLabel label) {
        // Establece el texto del JLabel
        label.setText("");
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Usuarios");
            // Crea un filtro con la cédula del usuario
            Document filtro = new Document("cedula", Logeo.getCedula());
            // Crea un documento con los datos a actualizar
            Document actu = new Document("$set", new Document(getBtn(), getInp()));
            // Actualiza el registro de usuario
            col.updateOne(filtro, actu);
            label.setText("Registro actualizado");
            label.setVisible(true);
            // Maneja la excepción
        } catch (Exception e) {
            label.setText("No se ha actualizado el registro: " + e.getMessage());
            label.setVisible(true);
        }
    }

    /**
     * Verifica si hay un registro seleccionado en la tabla.
     *
     * @param table1 La tabla a verificar.
     * @return true si hay un registro seleccionado, false de lo contrario.
     */
    public boolean seleccionarRegitro(JTable table1) {
        return table1.getSelectedRow() == -1;
    }

    /**
     * Verifica si la clave dada coincide con la clave en la base de datos.
     *
     * @param txt El texto de la clave a verificar.
     * @param lbl El JLabel donde se mostrará el mensaje de verificación.
     * @return true si la clave coincide, false de lo contrario.
     */
    public boolean verClave(String txt, JLabel lbl) {
        // Genera un hash SHA-256 de la clave con la contraseña dada
        String encodcl = generateHash2(txt);
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Usuarios");
            // Crea un documento con la cédula del usuario
            Document doc = new Document("cedula", Logeo.getCedula());
            // Busca el documento en la colección
            FindIterable<Document> iterable = col.find(doc);
            // Recorre los documentos de la colección
            for (Document document : iterable) {
                // Obtiene la clave del documento
                String clave = document.getString("clave");
                // Verifica si la clave coincide
                if (encodcl.equals(clave)) {
                    lbl.setText("");
                    return true;
                }
            }
            lbl.setText("Clave incorrecta");
            return false;
        }
    }
}