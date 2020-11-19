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
 * @author IanLu
 */
public class TelaEditarMateriaPrima extends javax.swing.JFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;
short clique = 0;
boolean click = false;

    public void listarMateriaPrima(){
            String sql="select nome from materiaprima";
            try {
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery(sql);
                while(rs.next()){
                    Ed_Mat.addItem(rs.getString("nome"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaAddVenda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
  
    public void listarFornecedor(){
        String sql="select nome from fornecedor";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while(rs.next()){
                ed_fornMat.addItem(rs.getString("nome"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Pesquise uma matéria prima");
            //Logger.getLogger(TelaEditarFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public int pegarIdMateriaPrima() throws SQLException{
        String sql = "select lote from materiaprima where nome = '" + Ed_Mat.getSelectedItem().toString() + "'";
        try{
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while(rs.next()){
                return rs.getInt("lote");
            }  
        }
        catch(SQLException ex){
            Logger.getLogger(TelaEditarMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int pegarIdFornecedor() throws SQLException{
        String sql = "select id from fornecedor where nome = '" + ed_fornMat.getSelectedItem().toString() + "'";
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
    
    public void MostrarMateriaPrima() throws SQLException{
        String sql = "select m.nome,m.quantidade,f.nome from materiaprima m inner join fornecedor f on m.id_fornecedor = f.id where lote = '" + pegarIdMateriaPrima() + "'";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery(sql);
            while(rs.next()){
                ed_nomeMat.setText(rs.getString("m.nome"));
                ed_qtdMat.setText(""+rs.getInt("m.quantidade"));
                ed_fornMat.setSelectedItem(rs.getString("f.nome"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TelaEditarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void UpdateMateriaPrima() throws SQLException{
        String sql = "update materiaprima set nome = '" + ed_nomeMat.getText() + "', quantidade = '" + ed_qtdMat.getText() + 
                 "',id_fornecedor = '" + pegarIdFornecedor() + 
                "'   where lote = '" +pegarIdMateriaPrima() + "'";
        

            try {
                pst = conexao.prepareStatement(sql);
            //executando o banco
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso");
                this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(TelaAddVenda.class.getName()).log(Level.SEVERE, null, ex);
            } 
    }
    

    /**
     * Creates new form TelaAddMateriaPrima
     */
    public TelaEditarMateriaPrima() throws ClassNotFoundException {
        conexao = Database.conector();
        initComponents();
        listarMateriaPrima();
        click = false;
        clique = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        ed_nomeMat = new javax.swing.JTextField();
        ed_qtdMat = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        Ed_Mat = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        ed_fornMat = new javax.swing.JComboBox<>();

        jScrollPane1.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Materia Prima");

        jLabel2.setText("Fornecedor");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Quantidade");

        jButton2.setText("Editar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        ed_nomeMat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ed_nomeMatActionPerformed(evt);
            }
        });

        jLabel7.setText("Selecione a matéria prima");

        Ed_Mat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Ed_MatActionPerformed(evt);
            }
        });

        jLabel1.setText("Nome");

        jButton3.setText("Pesquisar");
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
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)
                            .addComponent(ed_nomeMat)
                            .addComponent(ed_qtdMat, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel2)
                                .addGap(160, 160, 160))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ed_fornMat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton2))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Ed_Mat, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jButton3)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ed_Mat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ed_nomeMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ed_fornMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ed_qtdMat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(47, 47, 47))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    if(ed_nomeMat.getText().isEmpty() || ed_qtdMat.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Preencha os campos corretamente");
    }else{
        if(!click){
            JOptionPane.showMessageDialog(null, "Pesquise uma Materia Prima");
        }else{
        try {
            UpdateMateriaPrima();
        } catch (SQLException ex) {
            Logger.getLogger(TelaEditarMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void ed_nomeMatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ed_nomeMatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ed_nomeMatActionPerformed

    private void Ed_MatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Ed_MatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Ed_MatActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    click = true;
    try {
        MostrarMateriaPrima();
        clique++;
        if(clique <= 1){
            listarFornecedor();
        }
        
    } catch (SQLException ex) {
        Logger.getLogger(TelaEditarMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(TelaEditarMateriaPrima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaEditarMateriaPrima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaEditarMateriaPrima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaEditarMateriaPrima.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new TelaEditarMateriaPrima().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TelaEditarMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Ed_Mat;
    private javax.swing.JComboBox<String> ed_fornMat;
    private javax.swing.JTextField ed_nomeMat;
    private javax.swing.JTextField ed_qtdMat;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
