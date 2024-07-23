import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

public class Registro extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JButton registrarButton;
    private JButton cancelarButton;
    private JPanel reg;

    public Registro() {
        setTitle("Registro");
        setContentPane(reg);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 600));
        pack();
        setVisible(true);
        setLocationRelativeTo(null);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new login();
                dispose();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MaskFormatter dateFormatter = new MaskFormatter("##/##/####");
                    dateFormatter.setPlaceholderCharacter('_');
                    nacimiento = new JFormattedTextField(dateFormatter);
                    nacimiento.setColumns(10);
                    reg.add(nacimiento, BorderLayout.NORTH);
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
}
