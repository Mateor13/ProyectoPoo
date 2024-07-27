package Forms;

import com.mongodb.client.*;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class buscarc extends JFrame {
    private JTable table1;
    private JButton buscarBtn;
    private JPanel Bus;
    private JButton regBtn;

    public buscarc() {
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("../icono/BACKING-BALON-FUTBOL-02.png"))).getImage());
        setTitle("Buscar Cliente");
        setContentPane(Bus);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 300));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);
        table1.setVisible(false);
        regBtn.setVisible(false);
        buscarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarBtn.setVisible(false);
                table1.setVisible(true);
                regBtn.setVisible(true);
                try (MongoClient moncli = MongoClients.create("mongodb+srv://mateo1309:Hola123456@analisis.qthwhia.mongodb.net/")) {
                    MongoDatabase db = moncli.getDatabase("futbolito");
                    MongoCollection<Document> col = db.getCollection("Clases.Usuarios");
                    Document doc = new Document();
                    FindIterable<Document> iterable = col.find(doc);
                }
            }
        });
        regBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new duenio();
                dispose();
            }
        });
    }
}
