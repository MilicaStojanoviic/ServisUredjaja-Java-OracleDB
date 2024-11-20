/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabele;

import domeni.Marka;
import domeni.Uredjaj;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class TableModelUredjaj extends AbstractTableModel{
    
    List<Uredjaj> list;
    final String[] columnNames = {"SifraUredjaja", "Naziv", "Tip","Marka"};
    final Class[] columnClasses = {Integer.class, String.class, String.class, String.class};

    public TableModelUredjaj(List<Uredjaj> list) {
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

   // @Override
   // public Class<?> getColumnClass(int columnIndex) {
  //      return columnClasses[columnIndex];
  //  }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Uredjaj u= this.list.get(rowIndex);
        switch(columnIndex){
            case 0:
                return u.getSifraUredjaja();
            case 1:
                return u.getNaziv();
            case 2:
                return u.getTip();
            case 3:
                return u.getMarka().getSifraMarke() + ", " + u.getMarka().getNaziv();
            default:
                return " ";
        }
    }
    
    public void setList(List<Uredjaj> list){
        this.list = list;
        fireTableDataChanged();
    }
    
    public Uredjaj getSelectedObject(int index){
        return this.list.get(index);
    }
    
}
