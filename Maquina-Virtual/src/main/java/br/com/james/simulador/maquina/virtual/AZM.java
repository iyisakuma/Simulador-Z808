package br.com.james.simulador.maquina.virtual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author yujisakuma
 */
public class AZM {
    
    private BufferedReader reader;
    private Map<String, Integer> tabelaSimbolos = new HashMap<>();
    /* 
    *  linha |endereço | label | operação | operando 1 | operando 2
     */
    private StringBuilder codigoObjeto = new StringBuilder();
    private JTextArea console;
    private JTextArea codigo;
    private int lineCounter = 0;
    private int pointCounter = 0;
    private String nomeArquivo;
    
    public AZM(String uri, JTextArea console, JTextArea codigo) throws IOException {
        var path = Paths.get(uri);
        reader = Files.newBufferedReader(path);
        nomeArquivo = uri.split("/")[uri.split("/").length - 1].replace(".txt", "");
        this.console = console;
        this.codigo = codigo;
    }
    
    public String init() throws IOException {
        String text = console.getText();
        var date = new Date();
        console.setText(text + "\n" + String.format("[%s]Iniciando o processo de montagem", date.toString()));
        return primeiroPasso();
    }
    
    private String[] limpa(String instrucao) {
        var instrucaoQuaseLimpa = instrucao.split(";")[0];
        return instrucaoQuaseLimpa.split("\\p{Zs}+");
    }
    
    private String primeiroPasso() throws IOException {
        String instruction;
        while ((instruction = reader.readLine()) != null) {
            var instrucaoLimpa = limpa(instruction);
            codigoObjeto.append(lineCounter).append(" \t");
            codigoObjeto.append(pointCounter).append("\t");
            switch (instrucaoLimpa[0]) {
                case "END":
                    codigoObjeto.append("END");
                    codigo.setText(codigoObjeto.toString());
                    return segundoPasso();
                case "ADD":
                case "DIV":
                case "SUB":
                case "MUL":
                case "CMP":
                case "AND":
                case "NOT":
                case "OR":
                case "XOR":
                case "JMP":
                case "JZ":
                case "NJZ":
                case "JP":
                case "CALL":
                case "POP":
                case "PUSHF":
                case "STORE":
                case "READ":
                case "WRITE":

                // Diretivas
                case "ORD":
                case "EQU":
                case "ORG":
                case "MOVE":
                    codigoObjeto.append(instrucaoLimpa[0]).append("\t");
                    if (instrucaoLimpa.length == 1) {
                        ++pointCounter;
                    }
                    if (instrucaoLimpa[1].contains(",")) {
                        var operando = instrucaoLimpa[1].split(",");
                        codigoObjeto.append(operando[0]).append("\t").append(operando[1]).append("\t");
                        pointCounter += 3;
                        //Se o segundo operando for label, será adicionado para tabela de simbolo
                        addTabelaSimbolo(operando[1]);
                    } else {
                        codigoObjeto.append(instrucaoLimpa[1]).append("\t");
                        pointCounter += 2;
                    }
                    codigoObjeto.append("\n");
                    break;
                case "POPF":
                case "PUSH":
                    ++pointCounter;
                    codigoObjeto.append(instrucaoLimpa[0]).append("\n");
                    break;
                default:
                    codigoObjeto.append(instrucaoLimpa[0])
                            .append("\t")
                            .append(instrucaoLimpa[1]).append("\t");
                    if (!tabelaSimbolos.containsKey(instrucaoLimpa[0])) {
                        tabelaSimbolos.put(instrucaoLimpa[0], pointCounter);
                    }
                    if (instrucaoLimpa.length == 2) {
                        ++pointCounter;
                    } else if (instrucaoLimpa[2].contains(",")) {
                        var operando = instrucaoLimpa[2].split(",");
                        codigoObjeto.append(operando[0]).append("\t").append(operando[1]).append("\t");
                        pointCounter += 3;
                        //Se o segundo operando for label, será adicionado para tabela de simbolo
                        addTabelaSimbolo(operando[1]);
                    } else {
                        codigoObjeto.append(instrucaoLimpa[2]);
                        
                        if (instrucaoLimpa[1].equals("EQU")) {
                            tabelaSimbolos.put(instrucaoLimpa[0], Integer.valueOf(instrucaoLimpa[2]));
                            ++pointCounter;
                        } else {
                            pointCounter += 2;
                        }
                    }
                    codigoObjeto.append("\n");
                    break;
            }
            ++lineCounter;
        }
        throw new AssertionError("O programa não possui END");
    }
    
    private String segundoPasso() throws IOException {
        String nomeArquivo = "..\\src\\main\\resources\\Executavel\\" + this.nomeArquivo + "Executavel.txt";
        
        FileWriter fileWriter = new FileWriter(nomeArquivo);
        var buffer = new BufferedWriter(fileWriter);
        Arrays.asList(codigoObjeto.toString().split("\n")).forEach(row -> {
            try {
                var instrucao = row.split("\t");
                var linhaBin = new StringBuilder();
                //linha | endereço | label | operação | operando 1 | operando 2
                linhaBin.append(instrucao[1]).append("_");
                switch (instrucao[2]) {
                    case "ADD":
                        linhaBin.append("00000011");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "SUB":
                        linhaBin.append("00101011");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "DIV":
                        linhaBin.append("11110111");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "MUL":
                        linhaBin.append("11111001");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "CMP":
                        if (instrucao[5].equals("DX")) { // endereçamento via registrador DX
                            linhaBin.append("00111011");
                        } else {
                            linhaBin.append("00111101");
                        }
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "AND":
                        if (instrucao[5].equals("AX") || instrucao[5].equals("DX")) {  // endereçamento via registrador AX
                            linhaBin.append("00100011");
                        } else {
                            linhaBin.append("00100101");
                        }
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "OR":
                        if (instrucao[5].equals("AX") || instrucao[5].equals("DX")) {  // endereçamento via registrador AX
                            linhaBin.append("00001011");
                        } else {
                            linhaBin.append("00001101");
                        }
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "XOR":
                        if (instrucao[5].equals("AX") || instrucao[5].equals("DX")) {  // endereçamento via registrador AX
                            linhaBin.append("00110011");
                        } else {
                            linhaBin.append("00110101");
                        }
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "NOT":
                        linhaBin.append("11111000");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "JMP":
                        linhaBin.append("11101011");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "JZ":
                        linhaBin.append("01110100");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "JNZ":
                        linhaBin.append("01110101");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "JP":
                        linhaBin.append("01111010");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "CALL":
                        linhaBin.append("11101000");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "RET":
                        linhaBin.append("11101111");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "END":
                        linhaBin.append("11101110");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "HLT":
                        linhaBin.append("11101110");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "POP":
                        if (instrucao[5].equals("AX") || instrucao[5].equals("DX")) {
                            linhaBin.append("01011000");
                        } else {
                            linhaBin.append("01011001");
                        }
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "POPF":
                        linhaBin.append("10011101");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "PUSH":
                        linhaBin.append("01010000");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "PUSHF":
                        linhaBin.append("10011100");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "STORE":
                        linhaBin.append("00000111");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "READ":
                        linhaBin.append("00010010");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                    case "WRITE":
                        String valor;
                        linhaBin.append("00001000");
                        linhaBin.append(getOperandoDe(instrucao)).append("\n");
                        buffer.write(linhaBin.toString());
                        break;
                }
                
            } catch (IOException ex) {
                Logger.getLogger(AZM.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        buffer.close();
        return nomeArquivo;
    }
    
    private void addTabelaSimbolo(String segundoOperando) {
        if (isNotLabel(segundoOperando)) {
            if (tabelaSimbolos.containsKey(segundoOperando)) {
                tabelaSimbolos.put(segundoOperando, Integer.MIN_VALUE);
            }
        }
    }
    
    private boolean isNotLabel(String str) {
        return str.replaceAll("AX|DX|SI", "").isBlank()
                && !str.contains("$")
                && !Character.isDigit(str.charAt(0));
    }
    
    private String getOperandoDe(String[] instrucao) {
        String operando = instrucao[instrucao.length - 1];
        var isDireto = operando.contains("&");
        if ("AX".equals(operando)) {
            return "11000000";
        }
        if ("DX".equals(operando)) {
            return "11000010";
        }
        if (tabelaSimbolos.containsKey(operando)) {
            var value = tabelaSimbolos.get(operando.replace("&", ""));
            var binario = String.format("%16s", Integer.toBinaryString(Integer.parseInt(String.valueOf(value)))).replaceAll(" ", "0");
            return isDireto ? binario.concat("0") : binario.concat("1");
        }
        var number = operando.replace("&", "");
        if (isNumeric(number)) {
            var binario = String.format("%16s", Integer.toBinaryString(Integer.parseInt(number))).replaceAll(" ", "0");
            return isDireto ? binario.concat("0") : binario.concat("1");
        } else {
            return "";
        }
    }
    
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
