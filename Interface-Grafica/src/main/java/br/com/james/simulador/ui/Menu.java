package br.com.james.simulador.ui;

import br.com.james.simulador.maquina.virtual.AZM;
import br.com.james.simulador.maquina.virtual.Executor;
import br.com.james.simulador.maquina.virtual.RegistradorEnum;
import static br.com.james.simulador.maquina.virtual.RegistradorEnum.RBM;
import static br.com.james.simulador.maquina.virtual.RegistradorEnum.REM;
import br.com.james.simulador.maquina.virtual.TipoEnderecamento;
import br.com.james.simulador.maquina.virtual.Util;
import br.com.james.simulador.maquina.virtual.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtCodigoArea = new javax.swing.JTextArea();
        btUploadFonte = new javax.swing.JButton();
        btBuild = new javax.swing.JButton();
        txtArquivoFonte = new javax.swing.JTextField();
        lblArquivoFonte = new javax.swing.JLabel();
        lblArquivoObjeto = new javax.swing.JLabel();
        txtArquivoObjeto = new javax.swing.JTextField();
        rdArquivoFonte = new javax.swing.JRadioButton();
        rdArquivoObjeto = new javax.swing.JRadioButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbRegistradores = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCodigo = new javax.swing.JTextArea();
        lvlInput = new javax.swing.JLabel();
        btInput = new javax.swing.JButton();
        txtInput = new javax.swing.JFormattedTextField();

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

        txtConsole.setEditable(false);
        txtConsole.setColumns(20);
        txtConsole.setRows(5);
        txtConsole.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane3.setViewportView(txtConsole);

        jTabbedPane2.addTab("Console", jScrollPane3);

        txtCodigoArea.setEditable(false);
        txtCodigoArea.setColumns(20);
        txtCodigoArea.setRows(5);
        jScrollPane5.setViewportView(txtCodigoArea);

        jTabbedPane2.addTab("Código", jScrollPane5);

        javax.swing.GroupLayout panelCodigoLayout = new javax.swing.GroupLayout(panelCodigo);
        panelCodigo.setLayout(panelCodigoLayout);
        panelCodigoLayout.setHorizontalGroup(
            panelCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCodigoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 720, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelCodigoLayout.setVerticalGroup(
            panelCodigoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCodigoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2))
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

        jTabbedPane1.addTab("Registradores", jScrollPane1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(jTable1);

        jTabbedPane1.addTab("Tabela de Simbolos", jScrollPane4);

        txtCodigo.setEditable(false);
        txtCodigo.setColumns(20);
        txtCodigo.setLineWrap(true);
        txtCodigo.setRows(5);
        txtCodigo.setText("-> BEM-VIND@ AO SIMULADOR Z808 ;)\nIntegrantes:\n* Claudinei Francisco  Rosa \n* Hyhickle Umetsubo Gonçalves\n* Igor Yui Ishihara Sakuma\n* Maurício Caralho Mucci\n\nTodos os direitos reservador para o grupo James\n\nhttps://www.youtube.com/shorts/9WXjN32MFhk");
        txtCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane2.setViewportView(txtCodigo);

        lvlInput.setText("Input");

        btInput.setIcon(new javax.swing.ImageIcon(getClass().getResource("/caixa-de-selecao.png"))); // NOI18N
        btInput.setText("ok");
        btInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btInputActionPerformed(evt);
            }
        });

        txtInput.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelCodigo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lvlInput, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 618, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btInput, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(panelTabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                    .addComponent(lblArquivoFonte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtArquivoFonte)
                                    .addComponent(lblArquivoObjeto, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
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
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblArquivoFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtArquivoFonte, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblArquivoObjeto))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lvlInput, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtArquivoObjeto, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(txtInput)
                    .addComponent(btInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelTabela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClearActionPerformed
        // TODO add your handling code here:
        txtCodigo.setText(null);
        txtArquivoFonte.setText(null);
        txtArquivoObjeto.setText(null);
    }//GEN-LAST:event_btClearActionPerformed

    private void btRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRunActionPerformed
        if (!txtArquivoObjeto.getText().isBlank()) {
            initRegistradores();
            initMemoria();
            
            var codigoAtual = Util.binarioParaDecimal(registradores.get(RegistradorEnum.IP));
            proximaInstrucaoEhRead(codigoAtual);
            try {
                processa();
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
                String text = txtConsole.getText();
                txtConsole.setText(text + "\n" + String.format("[%s] Foi realizado upload do arquivo: ", new Date().toString()) + filePath.getName());
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
            String nomeArquivoObjeto = new AZM(txtArquivoFonte.getText(), txtConsole, txtCodigoArea).init();
            var date = new Date();
            String text = txtConsole.getText();
            txtConsole.setText(text + "\n" + String.format("[%s]Processo de montagem finalizado!", date.toString()));
            txtArquivoObjeto.setText(nomeArquivoObjeto);
        } catch (IOException | IllegalArgumentException ex) {
            String text = txtConsole.getText();
            txtConsole.setText(text + "\n" + ex.getStackTrace());
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

    private void btInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btInputActionPerformed
        // TODO add your handling code here
        if (isRead && txtInput.getText() != null) {
            String text = txtInput.getText();
            isRead = false;
            btInput.setEnabled(false);
            txtInput.setText(null);
            txtInput.setEditable(false);
            registradores.replace(REM, String.format("%16s", Integer.toBinaryString(Integer.valueOf(text))).replaceAll(" ", "0"));
            processa();
        }
    }//GEN-LAST:event_btInputActionPerformed

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
    private javax.swing.JButton btInput;
    private javax.swing.JButton btRun;
    private javax.swing.JButton btUploadFonte;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblArquivoFonte;
    private javax.swing.JLabel lblArquivoObjeto;
    private javax.swing.JLabel lvlInput;
    private javax.swing.JPanel panelCodigo;
    private javax.swing.JPanel panelTabela;
    private javax.swing.JRadioButton rdArquivoFonte;
    private javax.swing.JRadioButton rdArquivoObjeto;
    private javax.swing.JTable tbRegistradores;
    private javax.swing.JTextField txtArquivoFonte;
    private javax.swing.JTextField txtArquivoObjeto;
    private javax.swing.JTextArea txtCodigo;
    private javax.swing.JTextArea txtCodigoArea;
    private javax.swing.JTextArea txtConsole;
    private javax.swing.JFormattedTextField txtInput;
    // End of variables declaration//GEN-END:variables

    private static final Map<RegistradorEnum, String> registradores = new HashMap();
    private static final Map<Integer, String> instrucoes = new HashMap();
    private static final Map<Integer, String> dados = new HashMap();
    private boolean isRead = false;
    
    private void initRegistradores() {
        Arrays.asList(RegistradorEnum.values())
                .stream()
                .forEach(registrador -> registradores.put(registrador, "0000000000000000"));
    }
    
    private void initMemoria() {
        try {
            BufferedReader reader = abreArquivoObjeto();
            String linha;
            while ((linha = reader.readLine()) != null) {
                var numeroLinha = Integer.valueOf(linha.split("_")[0]);
                var instrucao = linha.split("_")[1];
                if (instrucao.contains("#")) {
                    dados.put(numeroLinha, instrucao.substring(1));
                } else {
                    Menu.instrucoes.put(numeroLinha, instrucao);
                }
            }
            
        } catch (Exception ex) {
            txtConsole.setText("Nao foi possivel abrir o arquivo objeto.");
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
        String text = txtConsole.getText();
        txtConsole.setText(text + "\n" + String.format("[%s]Execução Finalizada", new Date().toString()));
    }
    
    private void mostraErro(Exception ex) {
        String text = txtConsole.getText();
        txtConsole.setText(text + "\n" + String.format("[%s]ERRO:%s", new Date().toString(), ex.getMessage()));
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
                    registradores.replace(RegistradorEnum.RBM, String.format("%16s", operando).replaceAll(" ", "0"));
                    return;
                case DIRETO:
                    var endereco = Util.binarioParaDecimal(operando);
                    var valor = dados.getOrDefault(endereco, "0");
                    registradores.replace(RegistradorEnum.RBM, String.format("%16s", valor).replaceAll(" ", "0"));
                    return;
            }
        }
    }
    
    private BufferedReader abreArquivoObjeto() throws IOException {
        var path = Paths.get(txtArquivoObjeto.getText());
        return Files.newBufferedReader(path);
    }
    
    private void atualizaMemoria(Map<RegistradorEnum, String> registradores) {
        if (registradores.get(RBM).contains("_")) {
            int endereco = Integer.valueOf(registradores.get(RBM).split("_")[0]);
            String value = registradores.get(RBM).split("_")[1];
            dados.put(endereco, value);
        }
    }
    
    private void proximaInstrucaoEhRead(int codigoAtual) {
        String instrucao = Menu.instrucoes.get(codigoAtual);
        String substring = instrucao.substring(0, 8);
        this.isRead = "00010010".equals(substring);
    }
    
    private void processa() {
        var codigoAtual = Util.binarioParaDecimal(registradores.get(RegistradorEnum.IP));
        while (ehFimExecucao(codigoAtual)) {
            if (!isRead) {
                var instrucao = Menu.instrucoes.get(codigoAtual);
                carregaRBM(instrucao);
                //Fim de execução
                if (instrucao.equals("11101110")) {
                    break;
                }
                var executor = new Executor(txtConsole);
                executor.run(instrucao, registradores);
                atualizaTabela(registradores);
                atualizaMemoria(registradores);
                codigoAtual = Util.binarioParaDecimal(registradores.get(RegistradorEnum.IP));
                proximaInstrucaoEhRead(codigoAtual);
            } else {
                btInput.setEnabled(true);
                txtInput.setEditable(true);
                return;
            }
        }
        atualizaTabela(registradores);
        finalizaExecucao();
    }
    
}
