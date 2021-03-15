/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Objetos.Cliente;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import Objetos.Program;
import static Objetos.Program.clientes;
import static Objetos.Program.funcionarios;
import static Objetos.Program.instrutor;
import static Objetos.Program.recepcionista;
import Objetos.Recepcionista;

/**
 *
 * @author deive
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
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
        jLabel2 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtSenha = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Login");
        setResizable(false);

        jLabel1.setText("Nome do Usuário :");

        jLabel2.setText("Senha :");

        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });

        jButton1.setText("Acessar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton2)
                            .addGap(28, 28, 28)
                            .addComponent(jButton1))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(106, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String usuario = txtUser.getText();
        String senha = new String(txtSenha.getPassword());

        if (((usuario.equals("")) || senha.equals(""))) {
            JOptionPane.showMessageDialog(this, "Campo de usuario ou senha em branco.");
        } else if (usuario.equals("adm") && senha.equals("adm")) //Primeiro verifico se é a chave mestre
        {
            new ControleDeAdms().setVisible(true);
            this.dispose();

        } else if (!instrutor.isEmpty() && instrutor.get(senha).getNome().equals(usuario))//Verificar se é treinador
        {
            new Instrutor().setVisible(true);
            this.dispose();

        } else if (!recepcionista.isEmpty() && recepcionista.get(senha).getNome().equals(usuario))//Verificar se é recepcionista
        {
            new Recepçao().setVisible(true);
            this.dispose();

        } else if (!clientes.isEmpty() && clientes.get(senha).getNome().equals(usuario))//verifica se é cliente 
        {
            JanelaDeCliente janela = new JanelaDeCliente();
            janela.setVisible(true);
            janela.enviarCliente(Program.clientes.get(senha));
            this.dispose();
        } else
            JOptionPane.showMessageDialog(this, "Nome de usuario ou senha invalidos.");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
             String usuario = txtUser.getText();
        String senha = new String(txtSenha.getPassword());

        if (((usuario.equals("")) || senha.equals(""))) {
            JOptionPane.showMessageDialog(this, "Campo de usuario ou senha em branco.");
        } else if (usuario.equals("adm") && senha.equals("adm")) //Primeiro verifico se é a chave mestre
        {
            new ControleDeAdms().setVisible(true);
            this.dispose();

        } else if (!instrutor.isEmpty() && instrutor.get(senha).getNome().equals(usuario))//Verificar se é treinador
        {
            new Instrutor().setVisible(true);
            this.dispose();

        } else if (!recepcionista.isEmpty() && recepcionista.get(senha).getNome().equals(usuario))//Verificar se é recepcionista
        {
            new Recepçao().setVisible(true);
            this.dispose();

        } else if (!clientes.isEmpty() && clientes.get(senha).getNome().equals(usuario))//verifica se é cliente 
        {
            JanelaDeCliente janela = new JanelaDeCliente();
            janela.setVisible(true);
            janela.enviarCliente(Program.clientes.get(senha));
            this.dispose();
        } else
            JOptionPane.showMessageDialog(this, "Nome de usuario ou senha invalidos.");
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String[] options = {"Sim", "Nao"};
        if (JOptionPane.showOptionDialog(this, "Deseja fechar?", "finalizar", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == 0) {

            this.dispose();

            Recepcionista r = null;
            Objetos.Instrutor i = null;
            Objetos.Cliente c = null;

            for (String key : clientes.keySet()) {
                c = clientes.get(key);
                c.writeFile();
            }

            for (String key : recepcionista.keySet()) {
                r = recepcionista.get(key);
                if (r.getNome() != null) {
                    r.writeFile();
                }
            }

            for (String key : instrutor.keySet()) {
                i = instrutor.get(key);
                if (i.getNome() != null) {
                    i.writeFile();
                }
            }

        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtSenha;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
