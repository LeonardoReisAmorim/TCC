/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Database;

/**
 *
 * @author Vinícius
 */
public class TelaEditarFornecedor extends javax.swing.JFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;
boolean clicou = false;

    public void listarFornecedor(){
        String sql="select nome from fornecedor";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while(rs.next()){
                Ed_forn.addItem(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaEditarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
    public int pegarIdFornecedor() throws SQLException{
        String sql = "select id from fornecedor where nome = '" + Ed_forn.getSelectedItem().toString() + "'";
        try{
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while(rs.next()){
                return rs.getInt("id");
            }
            
        }
        catch(SQLException ex){
            Logger.getLogger(TelaEditarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
     public void MostrarFornecedor() throws SQLException{
        String sql = "select * from fornecedor where id = '" + pegarIdFornecedor() + "'";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while(rs.next()){
                ed_nomeforn.setText(rs.getString("nome"));
                ed_cnpj.setText(""+rs.getString("cnpj"));
                ed_cidforn.setText(rs.getString("cidade"));
                ed_contato.setText(""+rs.getString("contato"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaEditarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void UpdateFornecedor() throws SQLException{
        String sql = "update fornecedor set nome = '" + ed_nomeforn.getText() + "', cnpj = '" + ed_cnpj.getText() + 
                "',cidade = '" +ed_cidforn.getText()+ "',contato = '" + ed_contato.getText()+ 
                "'   where id = '" + pegarIdFornecedor() + "'";
            try {
                pst = conexao.prepareStatement(sql);
            //executando o banco
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso");
                this.dispose();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Fornecedor nao pode ser atualizado");
                Logger.getLogger(TelaEditarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        
    }
    
    /**
     * Creates new form TelaEditarCliente
     */
    public TelaEditarFornecedor() throws ClassNotFoundException {
        conexao = Database.conector();
        initComponents();
        listarFornecedor();
        clicou = false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        ed_nomeforn = new javax.swing.JTextField();
        ed_cnpj = new javax.swing.JTextField();
        ed_cidforn = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        ed_contato = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        Ed_forn = new javax.swing.JComboBox<>();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Editar fornecedor");

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ed_nomeforn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ed_nomefornActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome da empresa");

        jLabel2.setText("CNPJ");

        jLabel3.setText("Cidade");

        jLabel4.setText("Contato");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Escolha o fornecedor para editar");

        jButton3.setText("Procurar Fornecedor");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(ed_nomeforn, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                            .addComponent(ed_cidforn))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ed_contato, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(ed_cnpj)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2))))
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Ed_forn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton3)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ed_forn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_nomeforn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed_cnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_cidforn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed_contato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(54, 54, 54))
        );

        setSize(new java.awt.Dimension(590, 337));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    if(clicou){
        if(ed_nomeforn.getText().equals("") || ed_cidforn.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
        }else{
                try {
                    UpdateFornecedor();
                }catch (SQLException ex) {
                    Logger.getLogger(TelaEditarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
    }else{
        JOptionPane.showMessageDialog(null, "Pesquise o fornecedor");
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ed_nomefornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ed_nomefornActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ed_nomefornActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja cancelar a edição do fornecedor?", "Atenção",JOptionPane.YES_NO_OPTION);
        if(confirmar==JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(null, "Edição cancelada");
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            clicou = true;
            MostrarFornecedor();
        } catch (SQLException ex) {
            Logger.getLogger(TelaEditarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaEditarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEditarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEditarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEditarFornecedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaEditarFornecedor().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaEditarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Ed_forn;
    private javax.swing.JTextField ed_cidforn;
    private javax.swing.JTextField ed_cnpj;
    private javax.swing.JTextField ed_contato;
    private javax.swing.JTextField ed_nomeforn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables
}
