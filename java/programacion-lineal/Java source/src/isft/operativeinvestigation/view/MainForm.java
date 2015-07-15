package isft.operativeinvestigation.view;

import isft.operativeinvestigation.controller.Controller;
import isft.operativeinvestigation.model.OperationType;

import java.awt.Component;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;

import javax.swing.JRadioButton;

public class MainForm extends JFrame implements GraphView {

    private static final long serialVersionUID = 4620082442064522862L;
    private JPanel contentPane;
    private JLabel lblNewLabel;
    private JScrollPane scrollPane;
    private CustomTable table;

    private Controller controller;
    private JTextField textField;
    private JTextField textField1;
    private JTextPane txtpnElPuntoptimo;
    JRadioButton rdbtnMinimizacin;
    JRadioButton rdbtnMaximizacin;

    public MainForm(Controller controller, boolean empty) {
        setIconImage(Toolkit
                .getDefaultToolkit()
                .getImage(
                        "C:\\Users\\maximiliano.najle\\Pictures\\mate-infusion-icon.jpg"));
        setTitle("Marble Solver ++");

        this.controller = controller;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 797, 460);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        lblNewLabel = new JLabel("Por favor, ingrese los datos de la consulta");
        lblNewLabel.setBounds(5, 11, 470, 404);
        contentPane.add(lblNewLabel);

        JButton btnNewButton = new JButton("Calcular");
        btnNewButton.addActionListener(new CalculateButtonListener());

        btnNewButton.setBounds(485, 392, 289, 23);
        contentPane.add(btnNewButton);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(485, 11, 289, 172);
        contentPane.add(scrollPane);

        if (empty) {
            Object[][] values = new Object[3][4];
            String[] names = { "Restricción", "X", "Y", "Límite" };
            table = new CustomTable(values, names);
        } else {
            table = new CustomTable();
            table.setModel(new DefaultTableModel(new Object[][] {
                    { "a", "3.8", "4", "10" }, { "b", "3", "5", "10" },
                    { "c", "4.12", "2", "10" }, }, new String[] {
                    "Restricci\u00F3n", "X", "Y", "L\u00EDmite" }));
        }
        scrollPane.setViewportView(table);

        JButton btnNewButton1 = new JButton("Agregar restricción");
        btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnNewButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                table.addEmptyRow();
            }
        });
        btnNewButton1.setBounds(485, 194, 140, 23);
        contentPane.add(btnNewButton1);

        JButton btnNewButton2 = new JButton("Eliminar restricción");
        btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 10));
        btnNewButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                int i = table.getSelectedRow();
                table.removeRow(i);
            }
        });
        btnNewButton2.setBounds(635, 194, 140, 23);
        contentPane.add(btnNewButton2);

        JLabel lblCostoDeX = new JLabel("Precio X");
        lblCostoDeX.setBounds(485, 228, 140, 14);
        contentPane.add(lblCostoDeX);

        textField = new JTextField();
        textField.setBounds(485, 242, 140, 20);
        contentPane.add(textField);
        textField.setText("10");
        textField.setColumns(10);

        JLabel lblCostoY = new JLabel("Precio Y");
        lblCostoY.setBounds(635, 228, 139, 14);
        contentPane.add(lblCostoY);

        textField1 = new JTextField();
        textField1.setBounds(635, 242, 139, 20);
        contentPane.add(textField1);
        textField1.setText("15");
        textField1.setColumns(10);

        txtpnElPuntoptimo = new JTextPane();
        txtpnElPuntoptimo.setBounds(485, 339, 286, 42);
        contentPane.add(txtpnElPuntoptimo);

        JLabel lblResultados = new JLabel("Resultados");
        lblResultados.setBounds(485, 326, 289, 14);
        contentPane.add(lblResultados);

        JLabel lblNewLabel1 = new JLabel("Tipo de operaci\u00F3n");
        lblNewLabel1.setBounds(485, 273, 289, 14);
        contentPane.add(lblNewLabel1);

        rdbtnMaximizacin = new JRadioButton("Maximizaci\u00F3n");
        rdbtnMaximizacin.setSelected(true);
        rdbtnMaximizacin.setBounds(481, 296, 109, 23);
        contentPane.add(rdbtnMaximizacin);

        rdbtnMinimizacin = new JRadioButton("Minimizaci\u00F3n");
        rdbtnMinimizacin.setBounds(635, 294, 109, 23);
        contentPane.add(rdbtnMinimizacin);

        ButtonGroup operation = new ButtonGroup();
        operation.add(rdbtnMinimizacin);
        operation.add(rdbtnMaximizacin);
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            try {
                double x = Double.parseDouble(textField.getText());
                double y = Double.parseDouble(textField1.getText());

                OperationType operation = rdbtnMaximizacin.isSelected() ? OperationType.MAXIMIZATION
                        : OperationType.MINIMIZATION;

                controller.processData(table.getValues(), table.getRowCount(),
                        x, y, operation);

                int width = lblNewLabel.getWidth();
                int height = lblNewLabel.getHeight();
                Container ancester = ((JComponent) event.getSource())
                        .getTopLevelAncestor();

                controller.getGraph((GraphView) ancester, width, height);

            } catch (NumberFormatException | NullPointerException e) {
                JOptionPane.showMessageDialog((Component) event.getSource(),
                        "Che, fijate que quedó algo sin completar",
                        "Datos incompletos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void showGraph(Result result) {
        lblNewLabel.setIcon(result.getGraphIcon());
        txtpnElPuntoptimo
                .setText(result.getPoint() + "\n" + result.getProfit());
        contentPane.updateUI();
    }
}
