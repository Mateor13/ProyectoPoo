package Clases;

import com.mongodb.client.*;
import org.bson.Document;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clase que se encarga de almacenar los datos de las canchas.
 * Esta clase se encarga de realizar las operaciones CRUD de las canchas.
 */
public class cancha {
    // Definiendo los atributos de la clase cancha.
    String numero, nombre, ubicacion, numeroJugadores, btn, input;

    /**
     * Constructor de la clase cancha.
     */
    public cancha() {
    }

    /**
     * Constructor de la clase cancha.
     * @param numero Número de la cancha.
     * @param nombre Nombre de la cancha.
     * @param ubicacion Ubicación de la cancha.
     * @param numeroJugadores Número de jugadores de la cancha.
     * @param btn Botón de la cancha.
     * @param input Input de la cancha.
     */
    public cancha(String numero, String nombre, String ubicacion, String numeroJugadores, String btn, String input) {
        this.numero = numero;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.numeroJugadores = numeroJugadores;
        this.btn = btn;
        this.input = input;
    }

    /**
     * Obtiene el número de la cancha.
     * @return Número de la cancha.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Establece el número de la cancha.
     * @param numero Número de la cancha.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtiene el nombre de la cancha.
     * @return Nombre de la cancha.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la cancha.
     * @param nombre Nombre de la cancha.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la ubicación de la cancha.
     * @return Ubicación de la cancha.
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Establece la ubicación de la cancha.
     * @param ubicacion Ubicación de la cancha.
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Obtiene el número de jugadores de la cancha.
     * @return Número de jugadores de la cancha.
     */
    public String getNumeroJugadores() {
        return numeroJugadores;
    }

    /**
     * Establece el número de jugadores de la cancha.
     * @param numeroJugadores Número de jugadores de la cancha.
     */
    public void setNumeroJugadores(String numeroJugadores) {
        this.numeroJugadores = numeroJugadores;
    }

    /**
     * Obtiene el botón de la cancha.
     * @return Botón de la cancha.
     */
    public String getBtn() {
        return btn;
    }

    /**
     * Establece el botón de la cancha.
     * @param btn Botón de la cancha.
     */
    public void setBtn(String btn) {
        this.btn = btn;
    }

    /**
     * Obtiene el valor a cambiar, en el metodo de actualizar.
     * @return Valor a cambiar.
     */
    public String getInput() {
        return input;
    }

    /**
     * Establece el valor a cambiar, en el metodo de actualizar.
     * @param input Valor a cambiar.
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Método que se encarga de verificar si el número de la cancha cumple con el formato de 5 digitos.
     *
     * @return True si el número de la cancha cumple con el formato de 5 digitos, false en caso contrario.
     */
    public boolean vernumCan() {
        // Definiendo el patrón de 5 digitos.
        Pattern patron = Pattern.compile("^\\d{5}$");
        // Verificando si el número de la cancha cumple con el patrón.
        Matcher matcher = patron.matcher(getNumero());
        // Retornando si el número de la cancha cumple con el patrón.
        return matcher.matches();
    }

    /**
     * Método que se encarga de verificar si el número de la cancha ya existe en la base de datos.
     *
     * @return True si el número de la cancha ya existe en la base de datos, false en caso contrario.
     */
    public boolean canExistente (){
        // Conectando a la base de datos de mongoDB.
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")){
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Canchas");
            // Creando un filtro para buscar el número de la cancha.
            Document filtro = new Document("id", getNumero());
            // Buscando el número de la cancha en la base de datos.
            FindIterable<Document> iter = col.find(filtro);
            for (Document _ : iter) {
                // Retornando si el número de la cancha existe en la base de datos.
                return true;
            }
        }
        // Retornando si el número de la cancha no existe en la base de datos.
        return false;
    }

    /**
     * Metodo que se encarga de verificar si el usuario selecciono un registro de la tabla.
     * @param table1 Tabla de la cual se selecciona el registro.
     * @return True si el usuario selecciono un registro, false en caso contrario.
     */
    public boolean seleccionarRegitro(JTable table1) {
        // Verificando columna seleccionada es diferente de -1 (Valor por defecto de una tabla).
        return table1.getSelectedRow() == -1;
    }

    /**
     * Método que se encarga traer los datos de mongoDB y mostrar las canchas en la tabla.
     *
     * @param tabla Tabla en la cual se muestran las canchas.
     */
    public void mostrarCanchas(JTable tabla) {
        // Creando un modelo de tabla.
        DefaultTableModel model = new DefaultTableModel() {
            // Sobreescribiendo el método getColumnClass para establecer el tipo de dato de la columna de la imagen.
            @Override
            public Class<?> getColumnClass(int column) {
                // Verificando si la columna es la columna de la imagen.
                if (column == 4) {
                    // Retornando el tipo de dato de la columna de la imagen.
                    return ImageIcon.class;
                }
                // Retornando el tipo de dato de la columna.
                return Object.class;
            }
        };
        // Estableciendo los encabezados de la tabla.
        model.setColumnIdentifiers(new Object[]{"Número Cancha", "Nombre", "Dirección", "Número jugadores", "Imagen"});
        // Asignando el modelo a la tabla.
        tabla.setModel(model);
        // Estableciendo el tamaño de las filas de la tabla.
        tabla.setRowHeight(100); // Ajustar el tamaño de la fila para la imagen
        // Estableciendo el color de fondo y de la fuente de la tabla.
        tabla.setBackground(new java.awt.Color(35, 35, 35));
        tabla.setForeground(new java.awt.Color(255, 255, 255));
        // Estableciendo el color de fondo y de la fuente de los encabezados de la tabla.
        tabla.getTableHeader().setBackground(new java.awt.Color(35, 35, 35));
        tabla.getTableHeader().setForeground(new java.awt.Color(255, 255, 255));
        // Conectando a la base de datos de MongoDB.
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            // Seleccionando la base de datos y la colección.
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Canchas");
            // Obteniendo los registros de la base de datos.
            FindIterable<Document> iter = col.find();
            // Recorriendo los registros de la base de datos.
            for (Document doc : iter) {
                // Obteniendo los datos de la base de datos.
                String numCancha = doc.getString("id");
                String nom = doc.getString("nombre");
                String dir = doc.getString("direccion");
                String numJug = doc.getString("numJugadores");
                // Obtener la cadena Base64 de la imagen y convertirla a bytes.
                String base64Image = doc.getString("imagen");
                byte[] ImagenData = Base64.getDecoder().decode(base64Image);
                // Convertir bytes a BufferedImage.
                ByteArrayInputStream bais = new ByteArrayInputStream(ImagenData);
                BufferedImage ImagenCan = ImageIO.read(bais);
                // Redimensionar la imagen.
                Image scaledImage = ImagenCan.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(scaledImage);
                // Agregando los datos a la tabla.
                model.addRow(new Object[]{numCancha, nom, dir, numJug, icon});
            }
        } catch (Exception ex) {
            ex.printStackTrace(); // Imprimir la traza de la excepción para depuración.
        }
    }

    /**
     * Metodo que se encarga de mostrar la tabla de canchas para los administradores.
     *
     * @param tabla Tabla en la cual se muestran las canchas.
     */
    public void mostrarCanchas2(JTable tabla) {
        // Creando un modelo de tabla.
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        // Estableciendo el tamaño de las filas de la tabla.
        tabla.setRowHeight(25);
        // Estableciendo el color de fondo y de la fuente de la tabla.
        tabla.setBackground(new java.awt.Color(35,35,35));
        tabla.setForeground(new java.awt.Color(255,255,255));
        // Estableciendo el color de fondo y de la fuente de los encabezados de la tabla.
        tabla.getTableHeader().setBackground(new java.awt.Color(35,35,35));
        tabla.getTableHeader().setForeground(new java.awt.Color(255,255,255));
        // Limpiando la tabla.
        model.setRowCount(0);
        // Estableciendo los encabezados de la tabla.
        model.addColumn("Número Cancha");
        model.addColumn("Nombre");
        model.addColumn("Dirección");
        model.addColumn("Número jugadores");
        // Conectando a la base de datos de mongoDB.
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            // Seleccionando la base de datos y la colección.
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Canchas");
            // Obteniendo los registros de la base de datos.
            FindIterable<Document> iter = col.find();
            // Recorriendo los registros de la base de datos.
            for (Document doc : iter) {
                // Obteniendo los datos de la base de datos.
                String NumCancha = (String) doc.get("id");
                String nom = doc.getString("nombre");
                String dir = doc.getString("direccion");
                String numJug = doc.getString("numJugadores");
                // Agregando los datos a la tabla.
                model.addRow(new Object[]{NumCancha, nom, dir, numJug});
            }
        }
    }

    /**
     * Método que se encarga de eliminar un registro de una cancha de la base de datos.
     *
     * @param codigo Identificador de la cancha a eliminar.
     */
    public void eliminarRegistro(String codigo) {
        // Conectando a la base de datos de mongoDB.
            try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                // Seleccionando la base de datos y la colección.
                MongoDatabase db = mongo.getDatabase("futbolito");
                MongoCollection<Document> col = db.getCollection("Canchas");
                // Eliminando el registro de la base de datos.
                Document filtro = new Document("id", codigo);
                // Eliminando el registro de la base de datos.
                col.deleteOne(filtro);
        }
    }

    /**
     * Método que se encarga de actualizar un registro de una cancha de la base de datos.
     *
     * @param label Label en el cual se muestra el resultado de la operación.
     */
    public void actualizarRegistro(JLabel label) {
        // Estableciendo el texto del label.
        label.setText("");
        // Conectando a la base de datos de mongoDB.
        try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
            // Seleccionando la base de datos y la colección.
            MongoDatabase db = mongo.getDatabase("futbolito");
            MongoCollection<Document> col = db.getCollection("Canchas");
            // Se crea un filtro para buscar el registro a actualizar.
            Document filtro = new Document("id", Logeo.getCodigo());
            // Se crea un documento con los datos a actualizar.
            Document update = new Document("$set", new Document(getBtn(), getInput()));
            // Se actualiza el registro en la base de datos.
            col.updateOne(filtro, update);
            // Estableciendo el texto del label si se actualiza correctamente.
            label.setText("Registro actualizado");
            label.setVisible(true);
        } catch (Exception e) {
            // Estableciendo el texto del label si no se actualiza.
            label.setText("No se ha actualizado el registro: " + e.getMessage());
            label.setVisible(true);
        }
    }
}
