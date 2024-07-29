package Forms;

import com.mongodb.client.*;
import org.bson.Document;
import Clases.cancha;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class editarCanchas extends JFrame {
    private JTextField num;
    private JButton actualizarBtn;
    private JButton buscarBtn;
    private JButton nomBtn;
    private JButton numJBtn;
    private JButton regresarBtn;
    private JButton dirBtn;
    private JTextField nomb;
    private JTextField dire;
    private JComboBox numJug;
    private JPanel edCancha;
    private JLabel nomTxt;
    private JLabel dirTxt;
    private JLabel nTxt;
    private JLabel numTxt;
    private JLabel ver1;
    private JLabel ver2;
    private JButton elegirBtn;

    private void inicializar() {
        nomBtn.setVisible(false);
        dirBtn.setVisible(false);
        numJBtn.setVisible(false);
        nomTxt.setVisible(false);
        nomb.setVisible(false);
        dirTxt.setVisible(false);
        dire.setVisible(false);
        nTxt.setVisible(false);
        numJug.setVisible(false);
        actualizarBtn.setVisible(false);
        ver2.setVisible(false);
        elegirBtn.setVisible(false);
    }
    cancha canc = new cancha();
    public editarCanchas() {
        setIconImage(new ImageIcon(getClass().getResource("../icono/cancha.png")).getImage());
        setTitle("Editar Canchas");
        setContentPane(edCancha);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        inicializar();

        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (num.getText().isEmpty()) {
                    ver1.setText("Ingrese el número de la cancha");
                } else {
                    canc.setNumero(num.getText());
                    try (MongoClient mongo = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                        MongoDatabase db = mongo.getDatabase("futbolito");
                        MongoCollection<Document> col = db.getCollection("Canchas");
                        Document doc = new Document("id", canc.getNumero());
                        FindIterable<Document> iterable = col.find(doc);
                        boolean Existe = false;
                        for (Document document : iterable) {
                            String num1 = document.getString("id");
                            if (canc.getNumero().equals(num1)) {
                                ver1.setText("Cancha encontrada");
                                Existe = true;
                                break;
                            }
                        }
                        if (Existe) {
                            num.setVisible(false);
                            numTxt.setVisible(false);
                            ver1.setVisible(false);
                            buscarBtn.setVisible(false);
                            nomBtn.setVisible(true);
                            dirBtn.setVisible(true);
                            numJBtn.setVisible(true);
                        } else {
                            ver1.setText("Cancha no encontrada");
                        }
                    }
                }
            }
        });

        nomBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCampos("nombre");
            }
        });

        dirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCampos("direccion");
            }
        });

        numJBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCampos("numJugadores");
            }
        });

        elegirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarOpciones();
            }
        });

        actualizarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                capturarInput(canc);
                canc.actualizarRegistro(ver2);
            }
        });
        regresarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new gestionCanchas();
                dispose();
            }
        });
    }
    private void mostrarOpciones() {
        nomBtn.setVisible(true);
        dirBtn.setVisible(true);
        numJBtn.setVisible(true);
        nomTxt.setVisible(false);
        nomb.setVisible(false);
        dirTxt.setVisible(false);
        dire.setVisible(false);
        nTxt.setVisible(false);
        numJug.setVisible(false);
        elegirBtn.setVisible(false);
        actualizarBtn.setVisible(false);
        ver2.setVisible(false);
        canc.setBtn(null);
        canc.setInput(null);
    }

    private void mostrarCampos(String campo) {
        nomBtn.setVisible(false);
        dirBtn.setVisible(false);
        numJBtn.setVisible(false);
        switch (campo) {
            case "nombre":
                nomTxt.setVisible(true);
                nomb.setVisible(true);
                break;
            case "direccion":
                dirTxt.setVisible(true);
                dire.setVisible(true);
                break;
            case "numJugadores":
                nTxt.setVisible(true);
                numJug.setVisible(true);
                break;
        }
        elegirBtn.setVisible(true);
        actualizarBtn.setVisible(true);
        canc.setBtn(campo);
    }

    private void capturarInput(cancha canc) {
        switch (canc.getBtn()) {
            case "nombre":
                canc.setInput(nomb.getText());
                break;
            case "direccion":
                canc.setInput(dire.getText());
                break;
            case "numJugadores":
                if (numJug.getSelectedIndex() == 0) {
                    ver2.setText("Elija el número de jugadores");
                } else {
                    canc.setInput(numJug.getSelectedItem().toString());
                }
                break;
        }
    }
}

