/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabele;

import domeni.RadniNalog;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Lenovo
 */
public class TableModelNalog extends AbstractTableModel{
    
    List<RadniNalog> list;
    final String[] columnNames ={"Datum izdavanja", "Opis greske", "Napomena", "Klijent","Odgovorni radnik", "Radnja", "Uredjaj","Marka", "Ukupan iznos"};

    public TableModelNalog(List<RadniNalog> list) {
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
        RadniNalog n = this.list.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
        switch (columnIndex) {
            case 0:
                return sdf.format(n.getDatumIzdavanja()).toUpperCase();
            case 1:
                return n.getOpisGreske();
            case 2:
                return n.getNapomena();
            case 3:
                return n.getKlijent().getImePrezimeK();
            case 4:
                return n.getRadnik().getImePrezime();
            case 5:
                return n.getRadnja().getNazivR();
            case 6:
                return n.getUredjaj().getNaziv();
            case 7:
                return n.getMarka().getNaziv();
            case 8:
                return n.getUkupanIznos();

            default:
                return " ";
        }
    }

    public void setList(List<RadniNalog> list) {
        this.list = list;
        fireTableDataChanged();
    }

    public RadniNalog getSelectedObject(int index) {
        return this.list.get(index);
    }
    
}
