package br.com.james.simulador.maquina.virtual;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.JTextArea;

/**
 *
 * @author yujisakuma
 */
public class AZM {
    
    private BufferedReader reader;
    private String[][] tabelaSimbolo;
    private JTextArea console;
    private int lc = 0 ;
    
    public AZM(String uri, JTextArea console) throws IOException {
        var path = Paths.get(uri);
        reader = Files.newBufferedReader(path);
        this.console = console;
        var linhas = reader.lines().collect(Collectors.toList()).size();
        /* [0]        [1]     [2]        [3]          [4]
        *  endereço | label | operação | operando 1 | operando 2
         */
        this.tabelaSimbolo =  new String[linhas][5];
    }
    
    public void init() {
        try {
            console.setText("-------Iniciando o processo de montagem...");
            primeiroPasso();
        } catch (IOException ex) {
            StringBuilder log = new StringBuilder(console.getText());
            log.append("\n" + ex.getMessage());
            console.setText(log.toString());
        }
    }
    
    private String retiraComentario(String instrucao) {
        return instrucao.split(";")[0];
    }
    
    private void primeiroPasso() throws IOException {
        String instruction;
        while ((instruction = reader.readLine()) != null) {
            instruction = retiraComentario(instruction);
            var label = getLabel(instruction);
            var opcode = getOperation(instruction);
            var operand = getOperando(instruction);
            switch (opcode) {
                case "END":
                    segundoPasso();
                    return;
                case "EQU":
//                    if (!tabelaSimbolo.containsKey(label)) {
//                        tabelaSimbolo.put(label, operand);
//                    }
                    break;
                case "ORG":
                    break;
                
            }
        }
    }
    
    public void segundoPasso() throws IOException {
        var lc = 0;
        String instruction;
        while ((instruction = reader.readLine()) != null) {
            String opcode = getOperation(instruction);
            String operando = getOperando(instruction);
            switch (opcode) {
                case "EQU":
                    break;
                case "END":
                    reader.close();
                    return;
                case "ORG":
                    lc = getValorOperando(instruction);
            }
        }
    }
    
    private String getOperation(String instruction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private String getOperando(String instruction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private int getValorOperando(String instruction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private String getLabel(String instruction) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
