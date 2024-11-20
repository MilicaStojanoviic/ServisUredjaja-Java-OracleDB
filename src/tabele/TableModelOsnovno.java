/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabele;

import domeni.Skladiste;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class TableModelOsnovno extends AbstractTableModel{
    List<Skladiste> list;
    final String[] columnNames = {"Sifra", "Naziv", "Adresa", "Mesto"};
    
    public TableModelOsnovno() {
        list = new ArrayList<>();
    }

    public TableModelOsnovno(List<Skladiste> list) {
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

//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        return columnClasses[columnIndex];
//    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Skladiste s = this.list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getSifraSkladista();
            case 1:
                return s.getNaziv();
            case 2:
                return s.getAdresa();
            case 3:
                return s.getMesto().getNaziv();
            default:
                return " ";
        }
    }

    public void setList(List<Skladiste> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public Skladiste getSelectedObject(int index) {
        return this.list.get(index);
    }
}
