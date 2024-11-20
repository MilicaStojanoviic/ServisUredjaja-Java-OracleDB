/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabele;

import domeni.VrstaDobra;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class TableModelVrstaDobra extends AbstractTableModel{

    List<VrstaDobra> list;
    final String[] columnNames = {"Naziv", "Jedinica mere", "Aktuelna cena"};

    public TableModelVrstaDobra(List<VrstaDobra> list) {
        this.list = list;
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VrstaDobra v = this.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return v.getNaziv();
            case 1:
                return v.getJm().getNazivJM();
            case 2:
                return v.getAktuelnaCena();
            default:
                return " ";
        }
    }
    
    public void setList(List<VrstaDobra> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public VrstaDobra getSelectedObject(int index) {
        return this.list.get(index);
    }
    
}
