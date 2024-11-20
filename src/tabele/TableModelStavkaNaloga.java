/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabele;

import domeni.StavkaRadnogNaloga;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class TableModelStavkaNaloga extends AbstractTableModel{

    List<StavkaRadnogNaloga> list;
    final String[] columnNames = {"Rb", "Dobro", "Kolicina","Jedinica mere", "Opis greske"};

    public TableModelStavkaNaloga(List<StavkaRadnogNaloga> list) {
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
        StavkaRadnogNaloga sn = this.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sn.getRb();
            case 1:
                return sn.getVrstaDobra().getNaziv();
            case 2:
                return sn.getKolicina();
            case 3:
                return sn.getVrstaDobra().getJm();
            case 4:
                return sn.getOpisGreske();
            default:
                return " ";
        }
    }

    public void setList(List<StavkaRadnogNaloga> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public StavkaRadnogNaloga getSelectedObject(int index) {
        return this.list.get(index);
    }
    
}
