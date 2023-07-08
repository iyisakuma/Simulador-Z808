package br.com.james.simulador.maquina.virtual;

import java.util.Map;

import static br.com.james.simulador.maquina.virtual.RegistradorEnum.*;

/**
 *
 * @author yujisakuma
 */
public enum Mnemonico implements Acao {
    
    /**
     * 1. Quando adicionar um mnemonico adicionar o seu opcode em binário.
     * 2. Certificar que tenha 1 byte ou 2 bytes (depende da instrução). Além
     * disso, implementar o metódo ação para cada Mnemonico novo!
     */
    JUMP("11101011", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            registradores.replace(IP, String.copyValueOf(instrucao.toCharArray(), 8, 16));
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return false;
        }
    },
    JZ("01110100", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            var valor = registradores.get(SR);
            if (valor != null && valor.charAt(7) == '1') { //Se ZF == 1
                registradores.replace(IP, String.copyValueOf(instrucao.toCharArray(), 8, 16));
            }
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return false;
        }
    },
    JNZ("01110101", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            var valor = registradores.get(SR);
            if (valor != null && valor.charAt(7) != '1') { //Se ZF != 1
                registradores.replace(IP, String.copyValueOf(instrucao.toCharArray(), 8, 16));
            }
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return false;
        }
    },
    JP("01111010", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            var valor = registradores.get(SR);
            if (valor != null && valor.charAt(6) == '0') { //Se SF = 0
                registradores.replace(IP, String.copyValueOf(instrucao.toCharArray(), 8, 24));
            }
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return false;
        }
    },
    READ("00010010", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return false;
        }
    },
    WRITE("00001000", 24) {
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return false;
        }
    },
    AND_REG_REG("00100011", 16){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            if (!isNumberOfBitsValid(instrucao)){
                throw new IllegalArgumentException(String.format("Instrução: %s possui %d bit(s) e deveria possuir %d bits!",
                        instrucao, instrucao.length(), this.getNumberOfBits()));
            }
            var ax = registradores.get(AX);
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(8, 16);
            var finalValue = new StringBuilder();
            
            if (operand.equalsIgnoreCase(DX.getEndereco())) {
                var dx = new StringBuilder(registradores.get(DX));
                for (int i = 0; i < ax.toCharArray().length; i++) {
                    finalValue.append(ax.charAt(i) == '0' || dx.charAt(i) == '0' ? '0' : '1');
                }
            } else if (operand.equalsIgnoreCase(AX.getEndereco())) {
                finalValue.append(ax);
            } else {
                throw new IllegalArgumentException(String.format("Operando: %s não existe!", operand));
            }
            
            fillRegisterSR(sr, finalValue, true);
            registradores.replace(SR, sr.reverse().toString());
            registradores.replace(AX, finalValue.toString());
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return number.length() == this.getNumberOfBits();
        }
    },
    AND_REG_OP("00100101", 24){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            if (!isNumberOfBitsValid(instrucao)){
                throw new IllegalArgumentException(String.format("Instrução: %s possui %d bit(s) e deveria possuir %d bits!",
                        instrucao, instrucao.length(), this.getNumberOfBits()));
            }
            var ax = registradores.get(AX);
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(8, 24);
            var finalValue = new StringBuilder();
            
            if (ax.equalsIgnoreCase("0".repeat(ax.toCharArray().length))){
                finalValue.append(ax);
            } else {
                for (int i = 0; i < ax.toCharArray().length; i++) {
                    finalValue.append(ax.charAt(i) == '0' || operand.charAt(i) == '0' ? '0' : '1');
                }
            }
            
            fillRegisterSR(sr, finalValue, true);
            registradores.replace(SR, sr.reverse().toString());
            registradores.replace(AX, finalValue.toString());
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return number.length() == this.getNumberOfBits();
        }
    },
    NOT_REG("11111000", 16){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            if (!isNumberOfBitsValid(instrucao)){
                throw new IllegalArgumentException(String.format("Instrução: %s possui %d bit(s) e deveria possuir %d bits!",
                        instrucao, instrucao.length(), this.getNumberOfBits()));
            }
            var ax = registradores.get(AX);
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(8, 16);
            var finalValue = new StringBuilder();
            
            if (operand.equalsIgnoreCase(AX.getEndereco())) {
                for (char bit : ax.toCharArray()) {
                    finalValue.append(bit == '1' ? '0' : '1');
                }
            } else {
                throw new IllegalArgumentException(String.format("Operando: %s não existe!", operand));
            }
            
            fillRegisterSR(sr, finalValue, true);
            registradores.replace(SR, sr.reverse().toString());
            registradores.replace(AX, finalValue.toString());
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return number.length() == this.getNumberOfBits();
        }
    },
    OR_REG_REG("00001011", 16){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            if (!isNumberOfBitsValid(instrucao)){
                throw new IllegalArgumentException(String.format("Instrução: %s possui %d bit(s) e deveria possuir %d bits!",
                        instrucao, instrucao.length(), this.getNumberOfBits()));
            }
            var ax = registradores.get(AX);
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(8, 16);
            var finalValue = new StringBuilder();
            
            if (operand.equalsIgnoreCase(DX.getEndereco())) {
                var dx = new StringBuilder(registradores.get(DX));
                for (int i = 0; i < ax.toCharArray().length; i++) {
                    finalValue.append(ax.charAt(i) == '1' || dx.charAt(i) == '1' ? '1' : '0');
                }
            } else if (operand.equalsIgnoreCase(AX.getEndereco())) {
                finalValue.append(ax);
            } else {
                throw new IllegalArgumentException(String.format("Operando: %s não existe!", operand));
            }
            
            fillRegisterSR(sr, finalValue, true);
            registradores.replace(SR, sr.reverse().toString());
            registradores.replace(AX, finalValue.toString());
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return number.length() == this.getNumberOfBits();
        }
    },
    OR_REG_OP("00001101", 24){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            if (!isNumberOfBitsValid(instrucao)){
                throw new IllegalArgumentException(String.format("Instrução: %s possui %d bit(s) e deveria possuir %d bits!",
                        instrucao, instrucao.length(), this.getNumberOfBits()));
            }
            var ax = registradores.get(AX);
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(8, 24);
            var finalValue = new StringBuilder();
            
            if (ax.equalsIgnoreCase("1".repeat(ax.toCharArray().length))){
                finalValue.append(ax);
            } else {
                for (int i = 0; i < ax.toCharArray().length; i++) {
                    finalValue.append(ax.charAt(i) == '1' || operand.charAt(i) == '1' ? '1' : '0');
                }
            }
            
            fillRegisterSR(sr, finalValue, true);
            registradores.replace(SR, sr.reverse().toString());
            registradores.replace(AX, finalValue.toString());
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return number.length() == this.getNumberOfBits();
        }
    },
    XOR_REG_REG("00110011", 16){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            if (!isNumberOfBitsValid(instrucao)){
                throw new IllegalArgumentException(String.format("Instrução: %s possui %d bit(s) e deveria possuir %d bits!",
                        instrucao, instrucao.length(), this.getNumberOfBits()));
            }
            var ax = registradores.get(AX);
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(8, 16);
            var finalValue = new StringBuilder();
            
            if (operand.equalsIgnoreCase(DX.getEndereco())) {
                var dx = new StringBuilder(registradores.get(DX));
                for (int i = 0; i < ax.toCharArray().length; i++) {
                    finalValue.append(ax.charAt(i) == dx.charAt(i) ? '0' : '1');
                }
            } else if (operand.equalsIgnoreCase(AX.getEndereco())) {
                finalValue.append("0".repeat(ax.toCharArray().length));
            } else {
                throw new IllegalArgumentException(String.format("Operando: %s não existe!", operand));
            }
            
            fillRegisterSR(sr, finalValue, true);
            registradores.replace(SR, sr.reverse().toString());
            registradores.replace(AX, finalValue.toString());
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return number.length() == this.getNumberOfBits();
        }
    },
    XOR_REG_OP("00110101", 24){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            if (!isNumberOfBitsValid(instrucao)){
                throw new IllegalArgumentException(String.format("Instrução: %s possui %d bit(s) e deveria possuir %d bits!",
                        instrucao, instrucao.length(), this.getNumberOfBits()));
            }
            var ax = registradores.get(AX);
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(8, 24);
            var finalValue = new StringBuilder();
            
            if (ax.equalsIgnoreCase("1".repeat(ax.toCharArray().length)) || ax.equalsIgnoreCase("0".repeat(ax.toCharArray().length))){
                finalValue.append("0".repeat(ax.toCharArray().length));
            } else {
                for (int i = 0; i < ax.toCharArray().length; i++) {
                    finalValue.append(ax.charAt(i) == operand.charAt(i) ? '0' : '1');
                }
            }
            
            fillRegisterSR(sr, finalValue, true);
            registradores.replace(SR, sr.reverse().toString());
            registradores.replace(AX, finalValue.toString());
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return number.length() == this.getNumberOfBits();
        }
    },
    CMP_REG_REG("00111011", 16){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            if (!isNumberOfBitsValid(instrucao)){
                throw new IllegalArgumentException(String.format("Instrução: %s possui %d bit(s) e deveria possuir %d bits!",
                        instrucao, instrucao.length(), this.getNumberOfBits()));
            }
            var ax = registradores.get(AX);
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(8, 16);
            char finalValue;
            
            if (operand.equalsIgnoreCase(DX.getEndereco())) {
                finalValue = ax.equalsIgnoreCase(registradores.get(DX)) ? '1' : '0';
            } else {
                throw new IllegalArgumentException(String.format("Operando: %s não existe!", operand));
            }
            
            sr.setCharAt(8, finalValue);
            registradores.replace(SR, sr.reverse().toString());
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return number.length() == this.getNumberOfBits();
        }
    },
    CMP_REG_OP("00111101", 24){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            if (!isNumberOfBitsValid(instrucao)){
                throw new IllegalArgumentException(String.format("Instrução: %s possui %d bit(s) e deveria possuir %d bits!",
                        instrucao, instrucao.length(), this.getNumberOfBits()));
            }
            var ax = registradores.get(AX);
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(8, 24);
            var finalValue = ax.equalsIgnoreCase(operand) ? '1' : '0';
            
            sr.setCharAt(8, finalValue);
            registradores.replace(SR, sr.reverse().toString());
            return registradores;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return number.length() == this.getNumberOfBits();
        }
    };
    private final String valorBinario;
    private final int numberOfBits;
    
    Mnemonico(String valorBinario, int numberOfBits) {
        this.valorBinario = valorBinario;
        this.numberOfBits = numberOfBits;
    }
    
    public int getNumberOfBits() {
        return numberOfBits;
    }
    
    // TODO: colocar os opcodes aqui!!!
    /**
     * @param bytes: bytens em String
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
            case "00100011" ->
            {
                return AND_REG_REG;
            }
            case "00100101" ->
            {
                return AND_REG_OP;
            }
            case "00001011" ->
            {
                return OR_REG_REG;
            }
            case "00001101" ->
            {
                return OR_REG_OP;
            }
            case "00110011" ->
            {
                return XOR_REG_REG;
            }
            case "00110101" ->
            {
                return XOR_REG_OP;
            }
            case "11111000" ->
            {
                return NOT_REG;
            }
            case "00111011" ->
            {
                return CMP_REG_REG;
            }
            case "00111101" ->
            {
                return CMP_REG_OP;
            }
            // -------------------------------------------------
            default ->
                throw new IllegalArgumentException("Não existe mnmônico equivale a " + bytes);
        }
    }
    
    private boolean isEven(String number) {
        return (number.split("1").length - 1) % 2 == 0;
    }
    private boolean isZero(String number) {
        return !number.contains("1");
    }
    
    //TODO: quando a operação não for lógica indicar o que deve ser feito para o CF, IF e OF
    public void fillRegisterSR(StringBuilder sr, StringBuilder finalValue, boolean isLogic) {
        sr.setCharAt(0, isLogic ? '0' : '1'); // CF (carry) = 0
        sr.setCharAt(6, isEven(finalValue.toString()) ? '1' : '0'); // PF (parity) = isEven()
        // sr.setCharAt(7, isEven(finalValue.toString()) ? '1' : '0');
        sr.setCharAt(8, isZero(finalValue.toString()) ? '1' : '0'); // ZF (zero) = isZero()
        sr.setCharAt(9, finalValue.charAt(0)); // SF (sign) = most significant bit
        sr.setCharAt(12, isLogic ? '0' : '1'); // OF (overflow) = 0
    }
}
