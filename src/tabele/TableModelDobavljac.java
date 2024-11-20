/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabele;

import domeni.Dobavljac;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class TableModelDobavljac extends AbstractTableModel{
    
    List<Dobavljac> list;
    final String[] columnNames={ "PIB", "Naziv", "Adresa"};

    public TableModelDobavljac(List<Dobavljac> list) {
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
        Dobavljac d=this.list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return d.getPIB();
            case 1:
                return d.getIme()+" "+d.getTip();
            case 2:
                return d.getAdresa()+ ", "+d.getMesto().getNaziv();
            default:
                return " ";
        }
    }
    
    public void setList(List<Dobavljac> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public Dobavljac getSelectedObject(int index) {
        return this.list.get(index);
    }
}
