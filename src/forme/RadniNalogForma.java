/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domeni.RadniNalog;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import kontroler.Controller;
import tabele.TableModelNalog;

/**
 *
 * @author Lenovo
 */
public class RadniNalogForma extends javax.swing.JFrame {

    /**
     * Creates new form RadniNalogForma
     */
    public RadniNalogForma() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNalog = new javax.swing.JTable();
        btnStavka = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        tblNalog.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblNalog);

        btnStavka.setText("Stavke naloga");
        btnStavka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStavkaActionPerformed(evt);
            }
        });

        btnAdd.setText("Dodaj novi nalog");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setText("Izmeni nalog");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnStavka, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 275, Short.MAX_VALUE)
                        .addComponent(btnAdd)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStavka)
                    .addComponent(btnAdd)
                    .addComponent(btnUpdate))
                .addGap(31, 31, 31))
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

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if(tblNalog.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(this, "Morate izabrati nalog koji zelite da izmenite!", "Greska", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                RadniNalog n = ((TableModelNalog) tblNalog.getModel()).getSelectedObject(tblNalog.getSelectedRow());
                AddNalogForma form = new AddNalogForma(this, true, n, "UPDATE");
                form.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        RadniNalog n=new RadniNalog();
        AddNalogForma form = new AddNalogForma(this, true, n, "INSERT");
        form.setVisible(true);
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnStavkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStavkaActionPerformed
        if(tblNalog.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(this, "Morate izabrati nalog!", "Greska", JOptionPane.ERROR_MESSAGE);
        }else{
            try {
                RadniNalog n = ((TableModelNalog) tblNalog.getModel()).getSelectedObject(tblNalog.getSelectedRow());
                StavkeNalogaForma form = new StavkeNalogaForma(this, true, n);
                form.setVisible(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnStavkaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RadniNalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RadniNalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RadniNalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RadniNalogForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RadniNalogForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnStavka;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNalog;
    // End of variables declaration//GEN-END:variables

    private void fillTable() {
        try {
            RadniNalog n=new RadniNalog();
            List<RadniNalog> list1 = Controller.getInstance().getNalogList(n);
            TableModelNalog modelR1 = new TableModelNalog(list1);
            tblNalog.setModel(modelR1);
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska prilikom ucitavanja!", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    public void refreshTable(){
        List<RadniNalog> shownN = loadObjects();
        TableModelNalog modelN = (TableModelNalog) tblNalog.getModel();
        modelN.setList(shownN);
        modelN.fireTableDataChanged();
    }
    
     private List<RadniNalog> loadObjects() {
        try {
            RadniNalog n=new RadniNalog();
            return Controller.getInstance().getNalogList(n);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
