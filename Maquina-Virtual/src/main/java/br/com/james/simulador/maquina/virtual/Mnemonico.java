package br.com.james.simulador.maquina.virtual;

import javax.swing.*;
import java.util.Map;

import static br.com.james.simulador.maquina.virtual.RegistradorEnum.*;

/**
 * @author yujisakuma
 */
public enum Mnemonico implements Acao {
    
    /**
     * 1. Quando adicionar um mnemonico adicionar o seu opcode em binário. 2.
     * Certificar que tenha 1 byte ou 2 bytes (depende da instrução). Além
     * disso, implementar o metódo ação para cada Mnemonico novo!
     */
    ADD_AX_REG("00000011", 16) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            //Transforma o Binario contido em AX em Número Decimal
            int numberAX = Integer.parseInt(ax, 2);
            int soma;
            var operand = instrucao.substring(8, 16);
            if (operand.equalsIgnoreCase(DX.getEndereco())) {
                var dx = registradores.get(DX);
                //Transforma o Binario contido em DX em Número Decimal
                int numberDX = Integer.parseInt(dx, 2);
                soma = numberAX + numberDX;
            } else if (operand.equalsIgnoreCase(AX.getEndereco())) {
                soma = numberAX + numberAX;
            } else {
                throw new IllegalArgumentException(String.format("Operando: %s não existe!", operand));
            }
            //Transforma o Número Decimal em Binario novamente
            String binary = Integer.toBinaryString(soma);
            var finalResult = String.format("%16s", binary).replace("", "0");
            registradores.replace(AX, finalResult);
            
            var sr = registradores.get(SR);
            char[] toCharArray = sr.toCharArray();
            toCharArray[3] = '0';
            toCharArray[6] = '0';
            toCharArray[7] = '0';
            toCharArray[9] = '0';
            toCharArray[15] = '0';
            sr = String.copyValueOf(toCharArray);
            registradores.replace(SR, sr);
            return registradores;
        }
    }, ADD_AX_OPD("00000101", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            var opd = registradores.get(RBM);
            //Transforma o Binario contido em AX e OPD em Número Decimal
            int numberAX = Integer.parseInt(ax, 2);
            int numberOPD = Integer.parseInt(opd, 2);
            int soma = numberAX + numberOPD;
            //Transforma o Número Decimal em Binario novamente
            String binary = Integer.toBinaryString(soma);
            var finalResult = String.format("%16s", binary).replace("", "0");
            registradores.replace(AX, finalResult);
            
            var sr = registradores.get(SR);
            char[] toCharArray = sr.toCharArray();
            toCharArray[3] = '0';
            toCharArray[6] = '0';
            toCharArray[7] = '0';
            toCharArray[9] = '0';
            toCharArray[15] = '0';
            sr = String.copyValueOf(toCharArray);
            registradores.replace(SR, sr);
            return registradores;
        }
    }, SUB_AX_REG("00101011", 16) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            //Transforma o Binario contido em AX em Número Decimal
            int numberAX = Integer.parseInt(ax, 2);
            int subtracao;
            var operand = instrucao.substring(8, 16);
            if (operand.equalsIgnoreCase(DX.getEndereco())) {
                var dx = registradores.get(DX);
                //Transforma o Binario contido em DX em Número Decimal
                int numberDX = Integer.parseInt(dx, 2);
                subtracao = numberAX - numberDX;
            } else if (operand.equalsIgnoreCase(AX.getEndereco())) {
                subtracao = 0;
            } else {
                throw new IllegalArgumentException(String.format("Operando: %s não existe!", operand));
            }
            //Transforma o Número Decimal em Binario novamente
            String binary = Integer.toBinaryString(subtracao);
            var finalResult = String.format("%16s", binary).replace("", "0");
            registradores.replace(AX, finalResult);
            
            var sr = registradores.get(SR);
            char[] toCharArray = sr.toCharArray();
            toCharArray[3] = '0';
            toCharArray[6] = '0';
            toCharArray[7] = '0';
            toCharArray[9] = '0';
            toCharArray[15] = '0';
            sr = String.copyValueOf(toCharArray);
            registradores.replace(SR, sr);
            return registradores;
        }
    }, SUB_AX_OPD("00100101", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            var opd = registradores.get(RBM);
            //Transforma o Binario contido em AX e OPD em Número Decimal
            int numberAX = Integer.parseInt(ax, 2);
            int numberOPD = Integer.parseInt(opd, 2);
            int subtracao = numberAX - numberOPD;
            //Transforma o Número Decimal em Binario novamente
            String binary = Integer.toBinaryString(subtracao);
            var finalResult = String.format("%16s", binary).replace("", "0");
            registradores.replace(AX, finalResult);
            
            var sr = registradores.get(SR);
            char[] toCharArray = sr.toCharArray();
            toCharArray[3] = '0';
            toCharArray[6] = '0';
            toCharArray[7] = '0';
            toCharArray[9] = '0';
            toCharArray[15] = '0';
            sr = String.copyValueOf(toCharArray);
            registradores.replace(SR, sr);
            return registradores;
        }
    }, JUMP("11101011", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            var endereco = registradores.get(RBM);
            registradores.replace(IP, endereco);
            return registradores;
        }
    }, JZ("01110100", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            var valor = registradores.get(SR);
            if (valor != null && valor.charAt(7) == '1') { //Se ZF == 1
                var endereco = registradores.get(RBM);
                registradores.replace(IP, endereco);
            }
            return registradores;
        }
    }, JNZ("01110101", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            var valor = registradores.get(SR);
            if (valor != null && valor.charAt(7) != '1') { //Se ZF != 1
                var endereco = registradores.get(RBM);
                registradores.replace(IP, endereco);
            }
            return registradores;
        }
    }, JP("01111010", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            var valor = registradores.get(SR);
            if (valor != null && valor.charAt(6) == '0') { //Se SF = 0
                var endereco = registradores.get(RBM);
                registradores.replace(IP, endereco);
            }
            return registradores;
        }
    }, READ("00010010", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            
        }
    }, WRITE("00001000", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }, AND_REG_REG("00100011", 16) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            var opcodeRegister = instrucao.substring(8, 16);
            var resultado = new StringBuilder();
            if (opcodeRegister.equalsIgnoreCase(DX.getEndereco())) {
                var dx = new StringBuilder(registradores.get(DX));
                for (int i = 0; i < ax.toCharArray().length; i++) {
                    resultado.append(ax.charAt(i) == '0' || dx.charAt(i) == '0' ? '0' : '1');
                }
            } else if (opcodeRegister.equalsIgnoreCase(AX.getEndereco())) {
                resultado.append(ax);
            } else {
                throw new IllegalArgumentException(String.format(
                        "Opcode: %s não representa o registrador AX(%s) ou DX(%s)",
                        opcodeRegister, AX.getEndereco(), DX.getEndereco()));
            }
            updateRegistradores(registradores, instrucao, resultado, true);
            return registradores;
        }
    }, AND_REG_OP("00100101", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            var opcodeOp = instrucao.substring(16, 24);
            opcodeOp = String
                    .format("%16s", opcodeOp)
                    .replaceAll(" ", opcodeOp.toCharArray()[0] == '0' ? "0" : "1");
            var resultado = new StringBuilder();
            for (int i = 0; i < ax.toCharArray().length; i++) {
                resultado.append(ax.charAt(i) == '0' || opcodeOp.charAt(i) == '0' ? '0' : '1');
            }
            updateRegistradores(registradores, instrucao, resultado, true);
            return registradores;
        }
    }, NOT_REG("11111000", 16) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            var opcodeRegister = instrucao.substring(8, 16);
            var resultado = new StringBuilder();
            if (opcodeRegister.equalsIgnoreCase(AX.getEndereco())) {
                for (char bit : ax.toCharArray()) {
                    resultado.append(bit == '1' ? '0' : '1');
                }
            } else {
                throw new IllegalArgumentException(String.format(
                        "Opcode: %s não representa o registrador AX(%s)",
                        opcodeRegister, AX.getEndereco()));
            }
            updateRegistradores(registradores, instrucao, resultado, true);
            return registradores;
        }
    }, OR_REG_REG("00001011", 16) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            var opcodeRegister = instrucao.substring(8, 16);
            var resultado = new StringBuilder();
            if (opcodeRegister.equalsIgnoreCase(DX.getEndereco())) {
                var dx = new StringBuilder(registradores.get(DX));
                for (int i = 0; i < ax.toCharArray().length; i++) {
                    resultado.append(ax.charAt(i) == '1' || dx.charAt(i) == '1' ? '1' : '0');
                }
            } else if (opcodeRegister.equalsIgnoreCase(AX.getEndereco())) {
                resultado.append(ax);
            } else {
                throw new IllegalArgumentException(String.format(
                        "Opcode: %s não representa o registrador AX(%s) ou DX(%s)",
                        opcodeRegister, AX.getEndereco(), DX.getEndereco()));
            }
            updateRegistradores(registradores, instrucao, resultado, true);
            return registradores;
        }
    }, OR_REG_OP("00001101", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            var opcodeOp = instrucao.substring(16, 24);
            opcodeOp = String
                    .format("%16s", opcodeOp)
                    .replaceAll(" ", opcodeOp.toCharArray()[0] == '0' ? "0" : "1");
            var resultado = new StringBuilder();
            for (int i = 0; i < ax.toCharArray().length; i++) {
                resultado.append(ax.charAt(i) == '1' || opcodeOp.charAt(i) == '1' ? '1' : '0');
            }
            updateRegistradores(registradores, instrucao, resultado, true);
            return registradores;
        }
    }, XOR_REG_REG("00110011", 16) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            var opcodeRegister = instrucao.substring(8, 16);
            var resultado = new StringBuilder();
            if (opcodeRegister.equalsIgnoreCase(DX.getEndereco())) {
                var dx = new StringBuilder(registradores.get(DX));
                for (int i = 0; i < ax.toCharArray().length; i++) {
                    resultado.append(ax.charAt(i) == dx.charAt(i) ? '0' : '1');
                }
            } else if (opcodeRegister.equalsIgnoreCase(AX.getEndereco())) {
                resultado.append("0".repeat(ax.toCharArray().length));
            } else {
                throw new IllegalArgumentException(String.format(
                        "Opcode: %s não representa o registrador AX(%s) ou DX(%s)",
                        opcodeRegister, AX.getEndereco(), DX.getEndereco()));
            }
            updateRegistradores(registradores, instrucao, resultado, true);
            return registradores;
        }
    }, XOR_REG_OP("00110101", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            var opcodeOp = instrucao.substring(16, 24);
            opcodeOp = String
                    .format("%16s", opcodeOp)
                    .replaceAll(" ", opcodeOp.toCharArray()[0] == '0' ? "0" : "1");
            var resultado = new StringBuilder();
            for (int i = 0; i < ax.toCharArray().length; i++) {
                resultado.append(ax.charAt(i) == opcodeOp.charAt(i) ? '0' : '1');
            }
            updateRegistradores(registradores, instrucao, resultado, true);
            return registradores;
        }
    }, CMP_REG_REG("00111011", 16) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            var opcodeRegister = instrucao.substring(8, 16);
            char resultado;
            if (opcodeRegister.equalsIgnoreCase(DX.getEndereco())) {
                var dx = registradores.get(DX);
                resultado = ax.equalsIgnoreCase(dx) ? '1' : '0';
            } else {
                throw new IllegalArgumentException(String.format(
                        "Opcode: %s não representa o registrador DX(%s)",
                        opcodeRegister, DX.getEndereco()));
            }
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            sr.setCharAt(8, resultado);
            registradores.replace(SR, sr.reverse().toString());
            return registradores;
        }
    }, CMP_REG_OP("00111101", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var ax = registradores.get(AX);
            var opcodeOp = instrucao.substring(16, 24);
            opcodeOp = String
                    .format("%16s", opcodeOp)
                    .replaceAll(" ", opcodeOp.toCharArray()[0] == '0' ? "0" : "1");
            var resultado = ax.equalsIgnoreCase(opcodeOp) ? '1' : '0';
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            sr.setCharAt(8, resultado);
            registradores.replace(SR, sr.reverse().toString());
            return registradores;
        }
    }, POP_REG("01011000", 16) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var opcodeRegister = instrucao.substring(8, 16);
            if (opcodeRegister.equalsIgnoreCase(DX.getEndereco())) {
                registradores.replace(DX, pilha.popEndereco());
            } else if (opcodeRegister.equalsIgnoreCase(AX.getEndereco())) {
                registradores.replace(AX, pilha.popEndereco());
            } else {
                throw new IllegalArgumentException(String.format(
                        "Opcode: %s não representa o registrador AX(%s) ou DX(%s)",
                        opcodeRegister, AX.getEndereco(), DX.getEndereco()));
            }
            registradores.replace(SP, pilha.peekEndereco());
            return registradores;
        }
    }, POP_OPD("01011001", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            registradores.replace(END, pilha.popEndereco());
            registradores.replace(SP, pilha.peekEndereco());
            return registradores;
        }
    }, POP_F("10011101", 8) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            registradores.replace(SR, pilha.popEndereco());
            registradores.replace(SP, pilha.peekEndereco());
            return registradores;
        }
    }, PUSH_REG("01010000", 16) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            var opcodeRegister = instrucao.substring(8, 16);
            if (opcodeRegister.equalsIgnoreCase(DX.getEndereco())) {
                pilha.pushEndereco(registradores.get(DX));
            } else if (opcodeRegister.equalsIgnoreCase(AX.getEndereco())) {
                pilha.pushEndereco(registradores.get(AX));
            } else {
                throw new IllegalArgumentException(String.format("""
                            Opcode: %s não representa o registrador AX(%s) ou DX(%s)
                            """, opcodeRegister, AX.getEndereco(), DX.getEndereco()));
            }
            registradores.replace(SP, pilha.peekEndereco());
            return registradores;
        }
    }, PUSH_F("10011100", 8) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console) {
            verifyNumberOfBits(instrucao, this.getNumberOfBits());
            pilha.pushEndereco(registradores.get(SR));
            registradores.replace(SP, pilha.peekEndereco());
            return registradores;
        }
    };
    
    private final String valorBinario;
    private final int numberOfBits;
    private static final PilhaDoSistema pilha = new PilhaDoSistema();
    
    Mnemonico(String valorBinario, int numberOfBits) {
        this.valorBinario = valorBinario;
        this.numberOfBits = numberOfBits;
    }
    
    /**
     * @param bytes: bytes em String
     * @return Mnemonico equivalente ao opcode
     */
    public static Mnemonico getByBytes(String bytes) {
        switch (bytes) {
            // -------------------------------------------------
            // DESVIO
            case "11101011" -> {
                return JUMP;
            }
            case "01110100" -> {
                return JZ;
            }
            case "01110101" -> {
                return JNZ;
            }
            case "01111010" -> {
                return JP;
            }
            // -------------------------------------------------
            // L/S
            case "00010010" -> {
                return READ;
            }
            case "00001000" -> {
                return WRITE;
            }
            // -------------------------------------------------
            // LÓGICAS
            case "00100011" -> {
                return AND_REG_REG;
            }
            case "00100101" -> {
                return AND_REG_OP;
            }
            case "00001011" -> {
                return OR_REG_REG;
            }
            case "00001101" -> {
                return OR_REG_OP;
            }
            case "00110011" -> {
                return XOR_REG_REG;
            }
            case "00110101" -> {
                return XOR_REG_OP;
            }
            case "11111000" -> {
                return NOT_REG;
            }
            case "00111011" -> {
                return CMP_REG_REG;
            }
            case "00111101" -> {
                return CMP_REG_OP;
            }
            // -------------------------------------------------
            // ARITMETICA
            case "00000011" -> {
                return ADD_AX_REG;
            }
            case "00000101" -> {
                return ADD_AX_OPD;
            }
            case "00101011" -> {
                return SUB_AX_REG;
            }
//          case "00100101" -> {
//              return SUB_AX_OPD;
//          }
            // -------------------------------------------------
            // PILHA
            case "01011000" -> {
                if (verifyEmptyStack()) throw new IllegalStateException("Pilha está vazia!");
                return POP_REG;
            }
            case "01011001" -> {
                if (verifyEmptyStack()) throw new IllegalStateException("Pilha está vazia!");
                return POP_OPD;
            }
            case "10011101" -> {
                if (verifyEmptyStack()) throw new IllegalStateException("Pilha está vazia!");
                return POP_F;
            }
            case "01010000" -> {
                if (verifyStackCapacity()) throw new StackOverflowError("A pilha atingiu capacidade máxima");
                return PUSH_REG;
            }
            case "10011100" -> {
                if (verifyStackCapacity()) throw new StackOverflowError("A pilha atingiu capacidade máxima");
                return PUSH_F;
            }
            // -------------------------------------------------
            default -> throw new IllegalArgumentException("Não existe mnmônico equivale a " + bytes);
        }
    }
    
    public int getNumberOfBits() {
        return numberOfBits;
    }
    
    public static boolean verifyEmptyStack() {
        return pilha.isEmpty();
    }
    
    public static boolean verifyStackCapacity() {
        return !pilha.isSizeUnderLimit();
    }
    
    public void verifyNumberOfBits(String instrucao, int numberOfBits) {
        if (!isNumberOfBitsValid(instrucao, numberOfBits))
            throw new IllegalArgumentException(String.format(
                    "Instrução: %s possui %d bit(s) e deveria possuir %d bits!",
                    instrucao, instrucao.length(), this.getNumberOfBits()));
        
    }
    
    public void updateRegistradores(Map<RegistradorEnum, String> registradores, String instrucao, StringBuilder resultado, boolean isLogic) {
        registradores.replace(AX, resultado.toString());
        registradores.replace(IP, fillRegisterIP(registradores.get(IP), instrucao));
        registradores.replace(SR, fillRegisterSR(new StringBuilder(registradores.get(SR)).reverse(), resultado, isLogic));
    }
    
    private String fillRegisterIP(String ip, String instrucao) {
        switch (instrucao.length()) {
            case 8 -> {
                var ipDecimal = Integer.parseInt(ip, 2);
                ipDecimal += 1;
                ip = String
                        .format("%16s", Integer.toBinaryString(ipDecimal))
                        .replaceAll(" ", ip.toCharArray()[0] == 0 ? "0" : "1");
                return ip;
            }
            case 16 -> {
                var ipDecimal = Integer.parseInt(ip, 2);
                ipDecimal += 2;
                ip = String
                        .format("%16s", Integer.toBinaryString(ipDecimal))
                        .replaceAll(" ", ip.toCharArray()[0] == 0 ? "0" : "1");
                return ip;
            }
            case 24 -> {
                var ipDecimal = Integer.parseInt(ip, 2);
                ipDecimal += 3;
                ip = String
                        .format("%16s", Integer.toBinaryString(ipDecimal))
                        .replaceAll(" ", ip.toCharArray()[0] == 0 ? "0" : "1");
                return ip;
            }
            default -> {
                return ip;
            }
        }
    }
    
    //TODO: quando a operação não for lógica indicar o que deve ser feito para o CF e OF
    public String fillRegisterSR(StringBuilder sr, StringBuilder resultado, boolean isLogic) {
        if (isLogic){
            sr.setCharAt(0,'0'); // CF (carry) = 0
            sr.setCharAt(12,'0'); // OF (overflow) = 0
        } else {
            sr.setCharAt(0,'1');
            sr.setCharAt(12,'1');
        }
        sr.setCharAt(6, isEven(resultado.toString()) ? '1' : '0'); // PF (parity) = isEven()
        sr.setCharAt(8, isZero(resultado.toString()) ? '1' : '0'); // ZF (zero) = isZero()
        sr.setCharAt(9, resultado.charAt(0)); // SF (sign) = most significant bit
        return sr.reverse().toString();
    }
    
    private boolean isEven(String number) {
        return (number.split("1").length - 1) % 2 == 0;
    }
    
    private boolean isZero(String number) {
        return !number.contains("1");
    }
    
}
