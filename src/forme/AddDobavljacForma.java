/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forme;

import domeni.Dobavljac;
import domeni.Mesto;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import kontroler.Controller;

/**
 *
 * @author Lenovo
 */
public class AddDobavljacForma extends javax.swing.JFrame {

    String operation;
    DobavljacForma parent;
    Dobavljac d;
    /**
     * Creates new form AddDobavljacForma
     */
    public AddDobavljacForma(DobavljacForma parent, boolean  modal, Dobavljac d, String operation) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.parent = parent;
        this.d = d;
        this.operation = operation;
        fillCmbMesto();
        if (operation.equals("UPDATE")) {
            adjustFields(d);
        }
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPIB = new javax.swing.JTextField();
        txtIme = new javax.swing.JTextField();
        txtTip = new javax.swing.JTextField();
        txtAdresa = new javax.swing.JTextField();
        cmbMesto = new javax.swing.JComboBox();
        btnDodaj = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        jLabel2.setText("PIB:");

        jLabel3.setText("Ime:");

        jLabel4.setText("Tip:");

        jLabel5.setText("Adresa:");

        jLabel6.setText("Mesto:");

        cmbMesto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnDodaj.setText("Dodaj dobavljaca");
        btnDodaj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDodajActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 201, Short.MAX_VALUE)
                        .addComponent(btnDodaj, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPIB)
                            .addComponent(txtIme)
                            .addComponent(txtTip)
                            .addComponent(txtAdresa)
                            .addComponent(cmbMesto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtPIB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtIme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtTip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtAdresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbMesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnDodaj)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDodajActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDodajActionPerformed
        try {
            if (this.operation.equals("INSERT")) {
                this.insert();
            } else if (this.operation.equals("UPDATE")) {
                this.update();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnDodajActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDodaj;
    private javax.swing.JComboBox cmbMesto;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtAdresa;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtPIB;
    private javax.swing.JTextField txtTip;
    // End of variables declaration//GEN-END:variables

    private void fillCmbMesto() {
        try {
            cmbMesto.removeAllItems();
            List<Mesto> mesta = Controller.getInstance().getMestaList();
            for (Mesto mesto : mesta) {
                cmbMesto.addItem(mesto);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greska prilikom ucitavanja mesta!", "Greska", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void adjustFields(Dobavljac d) {
        this.txtPIB.setText(String.valueOf(d.getPIB()));
        this.txtIme.setText(d.getIme());
        this.txtTip.setText(d.getTip());
        this.txtAdresa.setText(d.getAdresa());
        this.cmbMesto.setSelectedItem(d.getMesto());
        this.btnDodaj.setText("Izmeni dobavljaca");
        
        
        
    }

    private void insert() throws Exception {
        if( txtPIB.getText().isEmpty()|| txtIme.getText().isEmpty() || txtTip.getText().isEmpty() || txtAdresa.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Morate popuniti sva polja!");
            return;
        }
        
        long PIB=Long.parseLong(txtPIB.getText());
        String ime = txtIme.getText();
        String tip = txtTip.getText();
        String adresa=txtAdresa.getText();
        Mesto mesto = (Mesto) cmbMesto.getSelectedItem();
        
        Dobavljac d=new Dobavljac();
        d.setPIB(PIB);
        d.setIme(ime);
        d.setTip(tip);
        d.setAdresa(adresa);
        d.setMesto(mesto);
        
       /* if (Controller.getInstance().exists(d) == 1) {
            JOptionPane.showMessageDialog(this, "Ovakav rekord vac postoji!");
            this.setVisible(false);
            dispose();
            return;
        }*/
        
        Controller.getInstance().insert(d);
        JOptionPane.showMessageDialog(this, "Uspesno dodat novi dobavljac!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
        parent.refreshTable();
        this.setVisible(false);
        dispose();
    }

    private void update() throws Exception {
        if( txtPIB.getText().isEmpty()|| txtIme.getText().isEmpty() || txtTip.getText().isEmpty() || txtAdresa.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Morate popuniti sva polja!");
            return;
        }
        
        long PIB=Long.parseLong(txtPIB.getText());
        String ime = txtIme.getText();
        String tip = txtTip.getText();
        String adresa=txtAdresa.getText();
        Mesto mesto = (Mesto) cmbMesto.getSelectedItem();
        
       
        d.setPIB(PIB);
        d.setIme(ime);
        d.setTip(tip);
        d.setAdresa(adresa);
        d.setMesto(mesto);
        
       /* if (Controller.getInstance().exists(d) == 1) {
            JOptionPane.showMessageDialog(this, "Ovakav rekord vac postoji!");
            this.setVisible(false);
            dispose();
            return;
        }*/
        
        Controller.getInstance().update(d);
        JOptionPane.showMessageDialog(this, "Uspesno ste izmenili dobavljaca!", "Uspesno", JOptionPane.INFORMATION_MESSAGE);
        parent.refreshTable();
        this.setVisible(false);
        dispose();
    }

    
}
