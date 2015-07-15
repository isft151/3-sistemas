package isft.operativeinvestigation.view;

import java.io.Serializable;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CustomTable extends JTable implements Serializable {// Si, es
                                                                 // herencia
                                                                 // para
                                                                 // implementación,
    // lo sé y me la banco.
    private static final long serialVersionUID = -5608053847290898416L;

    public CustomTable(Object[][] values, String[] columNames) {
        TableModel model = new DefaultTableModel(values, columNames);
        this.setModel(model);
    }

    public CustomTable() {
    }

    public Object[][] getValues() {
        int n = getRowCount();

        Object[][] values = new Object[n][4];

        for (int i = 0; i < n; i++) {
            values[i][0] = getValueAt(i, 0);
            values[i][1] = getValueAt(i, 1);
            values[i][2] = getValueAt(i, 2);
            values[i][3] = getValueAt(i, 3);
        }

        return values;
    }

    public void removeRow(int index) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.removeRow(index);
    }

    public void addEmptyRow() {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(new Object[] { null, null, null, null });
    }
}
