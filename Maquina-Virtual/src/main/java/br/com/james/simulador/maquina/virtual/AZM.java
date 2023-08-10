package br.com.james.simulador.maquina.virtual;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
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
        }
        return "";
    }

    public String segundoPasso() throws IOException {
        var lc = 0;
        String instruction;
        while ((instruction = reader.readLine()) != null) {
            switch ("") {
                case "EQU":
                    break;
                case "END":
                    reader.close();
                    return "";
                case "ORG":
                    break;
            }
        }
        return "";
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
}
