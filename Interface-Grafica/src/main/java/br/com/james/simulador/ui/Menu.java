package br.com.james.simulador.ui;

import br.com.james.simulador.maquina.virtual.AZM;
import br.com.james.simulador.maquina.virtual.Executor;
import br.com.james.simulador.maquina.virtual.RegistradorEnum;
import br.com.james.simulador.maquina.virtual.TipoEnderecamento;
import br.com.james.simulador.maquina.virtual.Util;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author xandi
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    public Menu() {
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

        jProgressBar1 = new javax.swing.JProgressBar();
        DialogErro = new javax.swing.JDialog();
        buttonGroup1 = new javax.swing.ButtonGroup();
        btRun = new javax.swing.JButton();
        btClear = new javax.swing.JButton();
        panelTabela = new javax.swing.JPanel();
        panelCodigo = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextArea();
        labelConsole = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        btUploadFonte = new javax.swing.JButton();
        btBuild = new javax.swing.JButton();
        txtArquivoFonte = new javax.swing.JTextField();
        lblArquivoFonte = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRegistradores = new javax.swing.JTable();
        lblArquivoObjeto = new javax.swing.JLabel();
        txtArquivoObjeto = new javax.swing.JTextField();
        rdArquivoFonte = new javax.swing.JRadioButton();
        rdArquivoObjeto = new javax.swing.JRadioButton();

        javax.swing.GroupLayout DialogErroLayout = new javax.swing.GroupLayout(DialogErro.getContentPane());
        DialogErro.getContentPane().setLayout(DialogErroLayout);
        DialogErroLayout.setHorizontalGroup(
            DialogErroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        DialogErroLayout.setVerticalGroup(
            DialogErroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simulador Z808");
        setIconImage((new javax.swing.ImageIcon(getClass().getResource("/iconLaunch.png")).getImage()));

        btRun.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botaoPlayIMG.png"))); // NOI18N
        btRun.setText("Run");
        btRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRunActionPerformed(evt);
            }
        });

        btClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botaoClearIMG.png"))); // NOI18N
        btClear.setText("Clear");
        btClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTabelaLayout = new javax.swing.GroupLayout(panelTabela);
        panelTabela.setLayout(panelTabelaLayout);
        panelTabelaLayout.setHorizontalGroup(
            panelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelTabelaLayout.setVerticalGroup(
            panelTabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        txtCodigo.setEditable(false);
        txtCodigo.setColumns(20);
        txtCodigo.setLineWrap(true);
        txtCodigo.setRows(5);
        txtCodigo.setText("-> BEM-VIND@ AO SIMULADOR Z808 ;)\nIntegrantes:\n* Claudinei Francisco  Rosa \n* Hyhickle Umetsubo Gonçalves\n* Igor Yui Ishihara Sakuma\n* Maurício Caralho Mucci\n\nTodos os direitos reservador para o grupo James\n\nhttps://www.youtube.com/shorts/9WXjN32MFhk");
        txtCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(txtCodigo);

        labelConsole.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        labelConsole.setText("Console");

        txtConsole.setEditable(false);
        txtConsole.setColumns(20);
        txtConsole.setRows(5);
        txtConsole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(txtConsole);

        javax.swing.GroupLayout panelCodigoLayout = new javax.swing.GroupLayout(panelCodigo);
        panelCodigo.setLayout(panelCodigoLayout);
        panelCodigoLayout.setHorizontalGroup(
            panelCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 702, Short.MAX_VALUE)
            .addGroup(panelCodigoLayout.createSequentialGroup()
                .addComponent(labelConsole, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(panelCodigoLayout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        panelCodigoLayout.setVerticalGroup(
            panelCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelConsole)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btUploadFonte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/botaoUploadIMG .png"))); // NOI18N
        btUploadFonte.setText("Upload ");
        btUploadFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUploadFonteActionPerformed(evt);
            }
        });

        btBuild.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ferramentas.png"))); // NOI18N
        btBuild.setText("Build");
        btBuild.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuildActionPerformed(evt);
            }
        });

        txtArquivoFonte.setEditable(false);
        txtArquivoFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtArquivoFonteActionPerformed(evt);
            }
        });

        lblArquivoFonte.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblArquivoFonte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/lista.png"))); // NOI18N
        lblArquivoFonte.setText("Arquivo Fonte");

        tbRegistradores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbRegistradores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"CL", "0000000000000000b"},
                {"RI", "0000000000000000b"},
                {"REM", "0000000000000000b"},
                {"RBM", "0000000000000000b"},
                {"AX", "0000000000000000b"},
                {"DX", "0000000000000000b"},
                {"SP", "0000000000000000b"},
                {"SI", "0000000000000000b"},
                {"SR", "0000000000000000b"}
            },
            new String [] {
                "Name", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbRegistradores);
        if (tbRegistradores.getColumnModel().getColumnCount() > 0) {
            tbRegistradores.getColumnModel().getColumn(0).setResizable(false);
            tbRegistradores.getColumnModel().getColumn(1).setResizable(false);
        }

        lblArquivoObjeto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lblArquivoObjeto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/codigo-binario.png"))); // NOI18N
        lblArquivoObjeto.setText("Arquivo Objeto");

        txtArquivoObjeto.setEditable(false);
        txtArquivoObjeto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtArquivoObjetoActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdArquivoFonte);
        rdArquivoFonte.setSelected(true);
        rdArquivoFonte.setText("Arquivo Fonte");
        rdArquivoFonte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdArquivoFonteActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdArquivoObjeto);
        rdArquivoObjeto.setText("Arquivo Objeto");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(panelTabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblArquivoFonte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(txtArquivoFonte)
                                    .addComponent(lblArquivoObjeto, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                                    .addComponent(txtArquivoObjeto)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btRun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btBuild)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btUploadFonte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdArquivoFonte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdArquivoObjeto)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRun)
                    .addComponent(btUploadFonte)
                    .addComponent(btBuild)
                    .addComponent(btClear)
                    .addComponent(rdArquivoFonte)
                    .addComponent(rdArquivoObjeto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblArquivoFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtArquivoFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblArquivoObjeto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtArquivoObjeto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelTabela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        // TODO add your handling code here:
        txtCodigo.setText(null);
        txtCodigo.setText("Faça upload de um arquivo com a extensão .txt que contenha as instruções de máquina.");
        txtArquivoFonte.setText(null);
        txtArquivoObjeto.setText(null);
    }//GEN-LAST:event_btClearActionPerformed

    private void btRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRunActionPerformed
        initRegistradores();
        if (!instrucoes.isEmpty()) {
            try {
                var codigoAtual = Util.binarioParaDecimal(registradores.get(RegistradorEnum.IP));
                while (ehFimExecucao(codigoAtual)) {
                    var instrucao = Menu.instrucoes.get(codigoAtual);
                    carregaRBM(instrucao);
                    //Fim de execução
                    if (instrucao.equals("11101110")) {
                        break;
                    }
                    var executor = new Executor(txtConsole);
                    executor.run(instrucao, registradores);
                    atualizaTabela(registradores);
                    codigoAtual = Util.binarioParaDecimal(registradores.get(RegistradorEnum.IP));
                }
                atualizaTabela(registradores);
                finalizaExecucao();
            } catch (Exception ex) {
                ex.printStackTrace();
                mostraErro(ex);
            }
        }
    }//GEN-LAST:event_btRunActionPerformed

    private void btUploadFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUploadFonteActionPerformed
        if (evt.getSource() == btUploadFonte) {
            var fileUpload = new JFileChooser();
            fileUpload.setCurrentDirectory(new File("."));

            txtArquivoFonte.setText(null);
            txtArquivoObjeto.setText(null);
            var res = fileUpload.showSaveDialog(null);
            if (res == JFileChooser.APPROVE_OPTION) {
                File filePath = new File(fileUpload.getSelectedFile().getAbsolutePath());
                txtConsole.setText("-------------------------- Foi realizado upload do arquivo: " + filePath.getName() + " --------------------------");
                if (rdArquivoFonte.isSelected()) {
                    txtArquivoFonte.setText(filePath.toString());
                } else {
                    txtArquivoObjeto.setText(filePath.toString());
                }
            }
        }
    }//GEN-LAST:event_btUploadFonteActionPerformed

    private void txtArquivoFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtArquivoFonteActionPerformed

    }//GEN-LAST:event_txtArquivoFonteActionPerformed

    private void btBuildActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuildActionPerformed
        try {
            valida();
            String nomeArquivoObjeto = new AZM(txtArquivoFonte.getText(), txtConsole).init();
            txtConsole.setText("Processo de montagem finalizado!\n");
            txtArquivoObjeto.setText(nomeArquivoObjeto);
        } catch (IOException | IllegalArgumentException ex) {
            txtConsole.setText(ex.getMessage());
        }
    }//GEN-LAST:event_btBuildActionPerformed

    private void valida() throws IllegalArgumentException {
        var arquivoFonte = txtArquivoFonte.getText();
        if (arquivoFonte.isEmpty()) {
            throw new IllegalArgumentException("------>É necessário adicionar um arquivo fonte.");
        }
    }
    private void txtArquivoObjetoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtArquivoObjetoActionPerformed

    }//GEN-LAST:event_txtArquivoObjetoActionPerformed

    private void rdArquivoFonteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdArquivoFonteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdArquivoFonteActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog DialogErro;
    private javax.swing.JButton btBuild;
    private javax.swing.JButton btClear;
    private javax.swing.JButton btRun;
    private javax.swing.JButton btUploadFonte;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelConsole;
    private javax.swing.JLabel lblArquivoFonte;
    private javax.swing.JLabel lblArquivoObjeto;
    private javax.swing.JPanel panelCodigo;
    private javax.swing.JPanel panelTabela;
    private javax.swing.JRadioButton rdArquivoFonte;
    private javax.swing.JRadioButton rdArquivoObjeto;
    private javax.swing.JTable tbRegistradores;
    private javax.swing.JTextField txtArquivoFonte;
    private javax.swing.JTextField txtArquivoObjeto;
    private javax.swing.JTextArea txtCodigo;
    private javax.swing.JTextArea txtConsole;
    // End of variables declaration//GEN-END:variables

    private static Map<RegistradorEnum, String> registradores = new HashMap();
    private static Map<Integer, String> instrucoes;
    private static Map<Integer, String> dados;

    private void initRegistradores() {
        Arrays.asList(RegistradorEnum.values())
                .stream()
                .forEach(registrador -> registradores.put(registrador, "0000000000000000"));
    }

    private void initMemoria(int linha, String instrucoes) {
        Menu.instrucoes = new HashMap();
        Menu.dados = new HashMap();
        if (!instrucoes.isEmpty()) {
            Arrays.asList(instrucoes.trim().split("\n"))
                    .forEach(instrucao -> {
                        if (instrucao.contains("#")) {
                            dados.put(linha, instrucao.substring(1));
                        } else {
                            Menu.instrucoes.put(linha, instrucao);
                        }
                        return;
                    });
        }
    }

    private void atualizaTabela(Map<RegistradorEnum, String> registradores) {
        var dtm = (DefaultTableModel) tbRegistradores.getModel();
        dtm.setRowCount(0);
        Arrays.asList(RegistradorEnum.values())
                .stream()
                .forEach(registrador -> dtm.addRow(new Object[]{registrador, registradores.get(registrador) + "b"}));
    }

    private void finalizaExecucao() {
        txtConsole.setText("---------------------------Execução Finalizada---------------------------");
    }

    private void mostraErro(Exception ex) {
        txtConsole.setText(ex.getMessage());
    }

    private boolean ehFimExecucao(int codigoAtual) {
        return codigoAtual >= 0 && codigoAtual < instrucoes.size();
    }

    private void carregaRBM(String instrucao) {
        var tamanhoInstrucao = instrucao.length();
        var operando = instrucao.substring(8, tamanhoInstrucao - 1);
        if (tamanhoInstrucao > 7 && tamanhoInstrucao % 2 == 1) {
            var tipoEnderecamento = TipoEnderecamento.getByBit(instrucao.charAt(tamanhoInstrucao - 1));
            switch (tipoEnderecamento) {
                case IMEDIATO:
                    registradores.replace(RegistradorEnum.RBM, operando);
                    return;
                case DIRETO:
                    var endereco = Util.binarioParaDecimal(operando);
                    if (endereco >= dados.size()) {
                        throw new IndexOutOfBoundsException("Endereço de memória inexistente");
                    }
                    var valor = dados.get(endereco);
                    registradores.replace(RegistradorEnum.RBM, valor);
                    return;
            }
        }
    }
}
