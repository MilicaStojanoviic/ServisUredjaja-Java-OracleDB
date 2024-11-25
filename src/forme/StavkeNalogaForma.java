/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domeni.RadniNalog;
import domeni.StavkaRadnogNaloga;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import kontroler.Controller;
import tabele.TableModelStavkaNaloga;

/**
 *
 * @author Lenovo
 */
public class StavkeNalogaForma extends javax.swing.JFrame {

    private RadniNalog nalog;
    RadniNalogForma parent;
    /**
     * Creates new form StavkaNalogaForma
     */
    public StavkeNalogaForma(RadniNalogForma parent, boolean modal, RadniNalog n) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.parent = parent;
        this.nalog = n;
        adjustFields();
        fillTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStavke = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpd = new javax.swing.JButton();
        btnUpd1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        jLabel1.setText("Nalog broj:");

        lbl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbl.setText("jLabel2");

        tblStavke.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblStavke);

        btnAdd.setText("Dodaj stavku");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpd.setText("Izmeni stavku");
        btnUpd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdActionPerformed(evt);
            }
        });

        btnUpd1.setText("Obrisi stavku");
        btnUpd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpd1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lbl))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpd, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpd1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lbl))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnUpd)
                    .addComponent(btnUpd1))
                .addGap(34, 34, 34))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        StavkaRadnogNaloga sn = new StavkaRadnogNaloga();
        sn.setRadniNalog(nalog);
        AddStavkaNalogaForma form = new AddStavkaNalogaForma(this, true, sn, "INSERT");
        form.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdActionPerformed
        if(tblStavke.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(this, "Morate izabrati stavku koju zelite da izmenite!", "Greska", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                StavkaRadnogNaloga stavka = ((TableModelStavkaNaloga) tblStavke.getModel()).getSelectedObject(tblStavke.getSelectedRow());
                AddStavkaNalogaForma form = new AddStavkaNalogaForma(this, true, stavka, "UPDATE");
                form.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpdActionPerformed

    private void btnUpd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpd1ActionPerformed
        if(tblStavke.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(this, "Morate izabrati stavku koju zelite da izbrisete!", "Greska", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                StavkaRadnogNaloga stavka = ((TableModelStavkaNaloga) tblStavke.getModel()).getSelectedObject(tblStavke.getSelectedRow());
                Controller.getInstance().delete(stavka);
                refreshTable();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpd1ActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnUpd;
    private javax.swing.JButton btnUpd1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl;
    private javax.swing.JTable tblStavke;
    // End of variables declaration//GEN-END:variables

    private void adjustFields() {
        lbl.setText(String.valueOf(nalog.getBrojNaloga()));
    }

    private void fillTable() {
        try {
            List<StavkaRadnogNaloga> list = Controller.getInstance().getStavkeNalogaList(nalog);
            TableModelStavkaNaloga modelSN = new TableModelStavkaNaloga(list);
            tblStavke.setModel(modelSN);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska prilikom ucitavanja!", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    public void refreshTable(){
        List<StavkaRadnogNaloga> shownSN = loadObjects();
        TableModelStavkaNaloga modelSN = (TableModelStavkaNaloga) tblStavke.getModel();
        modelSN.setList(shownSN);
        modelSN.fireTableDataChanged();
        parent.refreshTable();
    }
    
     private List<StavkaRadnogNaloga> loadObjects() {
        try {
            return Controller.getInstance().getStavkeNalogaList(nalog);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
