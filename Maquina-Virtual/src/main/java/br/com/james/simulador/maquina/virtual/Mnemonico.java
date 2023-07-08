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
    JUMP("11101011") {
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
    JZ("01110100") {
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
    JNZ("01110101") {
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
    JP("01111010") {
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
    AND_REG_REG("00100011"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
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
            return false;
        }
    },
    AND_REG_OP("00100101"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            var ax = registradores.get(AX);
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(8, 24);
            var finalValue = new StringBuilder();
            
            if (ax.contentEquals("0".repeat(ax.toCharArray().length))){
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
            return false;
        }
    },
    NOT_REG("11111000"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
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
            return false;
        }
    },
    OR_REG_REG("00001011"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
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
            return false;
        }
    },
    OR_REG_OP("00001101"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            var ax = registradores.get(AX);
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(8, 24);
            var finalValue = new StringBuilder();
            
            if (ax.contentEquals("1".repeat(ax.toCharArray().length))){
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
            return false;
        }
    },
    XOR_REG_REG("00110011"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            return null;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return false;
        }
    },
    XOR_REG_OP("00110101"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            return null;
        }
        
        @Override
        public boolean isNumberOfBitsValid(String number) {
            return false;
        }
    };
    private final String valorBinario;
    
    Mnemonico(String valorBinario) {
        this.valorBinario = valorBinario;
    }
    
    public String getValorBinario() {
        return valorBinario;
    }
    
    // TODO: todos os opcodes devem estar aqui!!!
    /**
     * @param bits: bits em String
     * @return Mnemonico equivalente ao opcode
     */
    public static Mnemonico getByBytes(String bits) {
        switch (bits) {
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
            // -------------------------------------------------
            default ->
                    throw new IllegalArgumentException("Não existe mnemônico que equivale a " + bits);
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
