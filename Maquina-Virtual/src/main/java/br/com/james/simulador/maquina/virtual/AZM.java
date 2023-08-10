package br.com.james.simulador.maquina.virtual;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
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
    private int lineCounter = 0;
    private int pointCounter = 0;

    public AZM(String uri, JTextArea console) throws IOException {
        var path = Paths.get(uri);
        reader = Files.newBufferedReader(path);
        this.console = console;
    }

    public String init() throws IOException {
        console.setText("-------Iniciando o processo de montagem...");
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
                    console.setText(codigoObjeto.toString());
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
                case "POPF":
                case "PUSH":
                case "PUSHF":
                case "STORE":
                case "READ":
                case "WRITE":

                // Diretivas
                case "ORD":
                case "EQU":
                case "ORG":
                    codigoObjeto.append(instrucaoLimpa[0]).append("\t");
                    if (instrucaoLimpa.length == 1) {
                        ++pointCounter;
                    }
                    if (instrucaoLimpa[1].contains(",")) {
                        var operando = instrucaoLimpa[1].split(",");
                        codigoObjeto.append(operando[0]).append(" ").append(operando[1]).append("\t");
                        pointCounter += 3;
                        //Se o segundo operando for label, será adicionado para tabela de simbolo
                        addTabelaSimbolo(operando[1]);
                    } else {
                        codigoObjeto.append(instrucaoLimpa[1]).append("\t");
                        pointCounter += 2;
                    }
                    codigoObjeto.append("\n");
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
        throw new AssertionError();
    }

    public String segundoPasso() throws IOException {
        String nomeArquivo = ".\\codigoObjeto.txt";

        FileWriter fileWriter = new FileWriter(nomeArquivo);
        var buffer = new BufferedWriter(fileWriter);
        Arrays.asList(codigoObjeto.toString().split("\n")).forEach(row -> {
            try {
                var instrucao = row.split("\t");
                var linhaBin = new StringBuilder();
                //linha | endereço | label | operação | operando 1 | operando 2
                linhaBin.append(instrucao[1]).append("_");
                switch (instrucao[3]) {
                    case "ADD":
                        linhaBin.append("00000011");
                        break;
                    case "SUB":
                        linhaBin.append("00101011");
                    case "DIV":
                        linhaBin.append("11110111");
                        break;
                    case "MUL":
                        linhaBin.append("11111001");
                        break;
                    case "CMP":
                        if (instrucao[5].equals("DX")) { // endereçamento via registrador DX
                            linhaBin.append("00111011");
                        } else {
                            linhaBin.append("00111101");
                        }
                        break;
                    case "and":
                        if (instrucao[5].equals("AX") || instrucao[5].equals("DX")) {  // endereçamento via registrador AX
                            linhaBin.append("00100011");
                        } else {
                            linhaBin.append("00100101");
                        }
                        break;
                    case "OR":
                        if (instrucao[5].equals("AX") || instrucao[5].equals("DX")) {  // endereçamento via registrador AX
                            linhaBin.append("00001011");
                        } else {
                            linhaBin.append("00001101");
                        }
                    case "XOR":
                        if (instrucao[5].equals("AX") || instrucao[5].equals("DX")) {  // endereçamento via registrador AX
                            linhaBin.append("00110011");
                        } else {
                            linhaBin.append("00110101");
                        }
                        break;
                    case "NOT":
                        linhaBin.append("11111000");
                        break;
                    case "JMP":
                        linhaBin.append("11101011");
                        break;
                    case "JZ":
                        linhaBin.append("01110100");
                        break;
                    case "JNZ":
                        linhaBin.append("01110101");
                        break;
                    case "JP":
                        linhaBin.append("01111010");
                        break;
                    case "call":
                        linhaBin.append("11101000");
                        break;
                    case "RET":
                        linhaBin.append("11101111");
                        break;
                    case "END":
                    case "HLT":
                        linhaBin.append("11101110");
                        break;
                    case "POP":
                        if (instrucao[5].equals("AX") || instrucao[5].equals("DX")) {
                            linhaBin.append("01011000");
                        } else {
                            linhaBin.append("01011001");
                        }
                        break;
                    case "POPF":
                        linhaBin.append("10011101");
                        break;
                    case "PUSH":
                        linhaBin.append("01010000");
                        break;
                    case "PUSHF":
                        linhaBin.append("10011100");
                        break;
                    case "STORE":
                        linhaBin.append("00000111");
                        break;
                    case "READ":
                        linhaBin.append("00010010");
                        break;
                    case "WRITE":
                        linhaBin.append("00001000");
                        break;
                }
                linhaBin.append(getOperandoDe(instrucao));
                buffer.write(linhaBin.toString());
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
        if(instrucao.length == 5){//Instrção com um operando apenas
            return "";
        }else{
            return "";
        }
//        if (textScanned[i][5].equals("AX")) {  // endereçamento via registrador AX
//            bufferedWriter.write("33C0");
//        } else if (textScanned[i][5].equals("DX")) { // endereçamento via registrador DX
//            bufferedWriter.write("33C2");
//        } else if (tabelaSimbolos.containsKey(textScanned[i][5])) {  // é uma label
//            bufferedWriter.write("34");
//            bufferedWriter.write(formataPara16Bits("" + tabelaSimbolos.get(textScanned[i][5])));
//        } else if (textScanned[i][5].contains("[")) { // endereçamento direto
//            bufferedWriter.write("35");
//            bufferedWriter.write(formataPara16Bits(textScanned[i][5].replace("[", "").replace("]", "")));
//        } else {    // endereçamento imediato
//            bufferedWriter.write("34");
//            bufferedWriter.write(formataPara16Bits(textScanned[i][5]));
//        }
    }
}
