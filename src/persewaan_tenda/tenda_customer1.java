/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persewaan_tenda;

/**
 *
 * @author ACER E1 32
 */
import koneksi.koneksidb;
import java.sql.*;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class tenda_customer1 extends javax.swing.JFrame {

     //membuat objek    
    private DefaultTableModel model;
    
    //deklarasi variabel
    String kdcustomer, nmcstmr , alamat ;
    int notlpn, noktp ;
    /**
     * Creates new form tenda_customer1
     */
    public tenda_customer1() {
        initComponents();
        //membuat obyek
        model = new DefaultTableModel();
        
        //memberi nama header pada tabel
        tblcustomer.setModel(model);
        model.addColumn("KODE CUSTOMER");
        model.addColumn("NAMA");
        model.addColumn("ALAMAT");
        model.addColumn("NO TELEPHONE");
        model.addColumn("KTP");
           
        //fungsi ambil data
        getDataCustomer();
    }
    //fungsi membaca data customer
    public void getDataCustomer(){
        //kosongkan tabel
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        //eksekusi koneksi dan kirimkan query ke database
        try{
            //tes koneksi
            Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
            //perintah sql untuk membaca data dari tabel gaji        
            String sql = "SELECT * FROM customer";
            ResultSet res = stat.executeQuery(sql);
            
             //baca data
            while(res.next()){
                //membuat obyek berjenis array
                Object[] obj = new Object[5];
                obj[0]=res.getString("kd_customer");
                obj[1]=res.getString("nama_customer");
                obj[2]=res.getString("alamat");
                obj[3]=res.getString("no_telephone");
                obj[4]=res.getString("ktp");
                model.addRow(obj);
            }
            }catch(SQLException err){
           JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    public void loadDataCustomer(){
        //mengambil data dari textbox dan menyimpan nilainya pada variabel
        kdcustomer = txtkdcstmr.getText();
        nmcstmr = txtnmcstmr.getText();
        alamat = txtalmt.getText();
        notlpn = Integer.parseInt(txttlp.getText());
        noktp = Integer.parseInt(txtktp.getText());
    }
    public void dataSelect(){
        //deklarasi variabel
        int i = tblcustomer.getSelectedRow();
        
        //uji adakah data di tabel?
        if(i == -1){
            //tidak ada yang terpilih atau dipilih.
            return;
        }
        txtkdcstmr.setText(""+model.getValueAt(i,0));
        txtnmcstmr.setText(""+model.getValueAt(i,1));
        txtalmt.setText(""+model.getValueAt(i,2));
        txttlp.setText(""+model.getValueAt(i,3));
        txtktp.setText(""+model.getValueAt(i,4));
    }
    public void reset(){
        kdcustomer = "";
        nmcstmr = "";
        alamat = "";
        notlpn = 0;
        noktp = 0;
        
        txtkdcstmr.setText(kdcustomer);
        txtnmcstmr.setText(nmcstmr);
        txtalmt.setText(alamat);
        txttlp.setText("");
        txtktp.setText("");
    }
    public void simpanDataCustomer(){
         //panggil fungsi load data
        loadDataCustomer();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String  sql =   "INSERT INTO customer(kd_customer, nama_customer, alamat, no_telephone, ktp )"
                            + "VALUES('"+ kdcustomer +"','"+ nmcstmr +"','"+ alamat +"','"+ notlpn +"','"+ noktp +"')";
            PreparedStatement p = (PreparedStatement) koneksidb.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
             //ambil data
            getDataCustomer();
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
     public void rubahDataCustomer(){
          //panggil fungsi load data
        loadDataCustomer();
        
        //uji koneksi dan eksekusi perintah
        try{
            //test koneksi
            Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
            
            //perintah sql untuk simpan data
            String sql  =   "UPDATE customer SET nama_customer  = '"+ nmcstmr +"',"
                            + "alamat  = '"+ alamat +"',"
                            + "no_telephone  = '"+ notlpn +"'," 
                            + "ktp  = '"+ noktp +"'"
                            + "WHERE kd_customer = '" + kdcustomer +"'";
            PreparedStatement p = (PreparedStatement) koneksidb.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            
            //ambil data
            getDataCustomer();
            
            reset();
            JOptionPane.showMessageDialog(null, "DATA CUSTOMER BERHASIL DIUBAH");
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    public void hapusDataCustomer(){
        //panggil fungsi ambil data
        loadDataCustomer(); 
        
        //Beri peringatan sebelum melakukan penghapusan data
        int pesan = JOptionPane.showConfirmDialog(null, "HAPUS DATA"+ kdcustomer +"?","KONFIRMASI", JOptionPane.OK_CANCEL_OPTION);
        
        //jika pengguna memilih OK lanjutkan proses hapus data
        if(pesan == JOptionPane.OK_OPTION){
            //uji koneksi
            try{
                //buka koneksi ke database
                Statement stat = (Statement) koneksidb.getKoneksi().createStatement();
                
                //perintah hapus data
                String sql = "DELETE FROM customer WHERE kd_customer='"+ kdcustomer +"'";
                PreparedStatement p =(PreparedStatement)koneksidb.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                
                //fungsi ambil data
                getDataCustomer();
                
                //fungsi reset data
                reset();
                JOptionPane.showMessageDialog(null, "DATA BERHASIL DIHAPUS");
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
            }
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
        jLabel1 = new javax.swing.JLabel();
        kdcstmr = new javax.swing.JLabel();
        nm = new javax.swing.JLabel();
        almt = new javax.swing.JLabel();
        tlp = new javax.swing.JLabel();
        ktp = new javax.swing.JLabel();
        txtkdcstmr = new javax.swing.JTextField();
        txtnmcstmr = new javax.swing.JTextField();
        txtalmt = new javax.swing.JTextField();
        txttlp = new javax.swing.JTextField();
        txtktp = new javax.swing.JTextField();
        tblReset = new javax.swing.JButton();
        tblRubah = new javax.swing.JButton();
        tblHapus = new javax.swing.JButton();
        tblKeluar = new javax.swing.JButton();
        tblSImpan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblcustomer = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("MASTER DATA CUSTOMER");

        kdcstmr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        kdcstmr.setText("KODE CUSTOMER");

        nm.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        nm.setText("NAMA CUSTOMER");

        almt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        almt.setText("ALAMAT");

        tlp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tlp.setText("NO TELEPHONE");

        ktp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ktp.setText("NO KTP");

        tblReset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblReset.setText("RESET");
        tblReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblResetActionPerformed(evt);
            }
        });

        tblRubah.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblRubah.setText("RUBAH");
        tblRubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblRubahActionPerformed(evt);
            }
        });

        tblHapus.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblHapus.setText("HAPUS");
        tblHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblHapusActionPerformed(evt);
            }
        });

        tblKeluar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblKeluar.setText("KELUAR");
        tblKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblKeluarActionPerformed(evt);
            }
        });

        tblSImpan.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        tblSImpan.setText("SIMPAN");
        tblSImpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tblSImpanActionPerformed(evt);
            }
        });

        tblcustomer.setModel(new javax.swing.table.DefaultTableModel(
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
        tblcustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblcustomerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblcustomer);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nm)
                                    .addComponent(almt)
                                    .addComponent(tlp)
                                    .addComponent(ktp)
                                    .addComponent(kdcstmr))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtkdcstmr, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                    .addComponent(txttlp, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtalmt, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtktp, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnmcstmr, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tblSImpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tblReset)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tblRubah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tblHapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tblKeluar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(kdcstmr)
                    .addComponent(txtkdcstmr, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtnmcstmr, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nm))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtalmt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(almt))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttlp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tlp))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ktp)
                    .addComponent(txtktp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tblSImpan)
                    .addComponent(tblReset)
                    .addComponent(tblRubah)
                    .addComponent(tblHapus)
                    .addComponent(tblKeluar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void tblResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblResetActionPerformed
        //memanggil fungsi reset
        reset();
    }//GEN-LAST:event_tblResetActionPerformed

    private void tblRubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblRubahActionPerformed
        //memanggil fungsi perbarui data kategori
        rubahDataCustomer();
    }//GEN-LAST:event_tblRubahActionPerformed

    private void tblHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblHapusActionPerformed
        //memanggil fungsi hapus data kategori
        hapusDataCustomer();
    }//GEN-LAST:event_tblHapusActionPerformed

    private void tblKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblKeluarActionPerformed
        //fungsi keluar dari master data kategori
        this.dispose();
    }//GEN-LAST:event_tblKeluarActionPerformed

    private void tblSImpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tblSImpanActionPerformed
        //memanggil fungsi simpan data kategori
        simpanDataCustomer();
    }//GEN-LAST:event_tblSImpanActionPerformed

    private void tblcustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblcustomerMouseClicked
        dataSelect();
    }//GEN-LAST:event_tblcustomerMouseClicked

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
            java.util.logging.Logger.getLogger(tenda_customer1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tenda_customer1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tenda_customer1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tenda_customer1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tenda_customer1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel almt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel kdcstmr;
    private javax.swing.JLabel ktp;
    private javax.swing.JLabel nm;
    private javax.swing.JButton tblHapus;
    private javax.swing.JButton tblKeluar;
    private javax.swing.JButton tblReset;
    private javax.swing.JButton tblRubah;
    private javax.swing.JButton tblSImpan;
    private javax.swing.JTable tblcustomer;
    private javax.swing.JLabel tlp;
    private javax.swing.JTextField txtalmt;
    private javax.swing.JTextField txtkdcstmr;
    private javax.swing.JTextField txtktp;
    private javax.swing.JTextField txtnmcstmr;
    private javax.swing.JTextField txttlp;
    // End of variables declaration//GEN-END:variables
}
