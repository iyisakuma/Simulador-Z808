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
    },
    AND_REG_REG("00100011"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            var ax = new StringBuilder(registradores.get(AX));
            var dx = new StringBuilder(registradores.get(DX));
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(7, 15);
            var finalValue = new StringBuilder();
            
            if (operand.equalsIgnoreCase(dx.toString())) {
                for (int i = 0; i < ax.toString().toCharArray().length; i++) {
                    finalValue.append(ax.charAt(i) == '0' || dx.charAt(i) == '0' ? '0' : '1');
                }
            } else if (operand.equalsIgnoreCase(ax.toString())) {
                finalValue.append(ax);
            } else
                throw new IllegalArgumentException();
            
            fillRegisterSR(sr, finalValue);
            registradores.replace(SR, sr.reverse().toString());
            registradores.replace(AX, finalValue.toString());
            return registradores;
        }
    },
    AND_REG_OP("00100101"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            return null;
        }
    },
    NOT_REG("11111000"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            var ax = new StringBuilder(registradores.get(AX));
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(7, 15);
            var finalValue = new StringBuilder();
            
            if (operand.equalsIgnoreCase(ax.toString())) {
                for (char bit : ax.toString().toCharArray()) {
                    finalValue.append(bit == '1' ? '0' : '1');
                }
            } else
                throw new IllegalArgumentException();
            
            fillRegisterSR(sr, finalValue);
            registradores.replace(SR, sr.reverse().toString());
            registradores.replace(AX, finalValue.toString());
            return registradores;
        }
    },
    OR_REG_REG("00001011"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            var ax = new StringBuilder(registradores.get(AX));
            var dx = new StringBuilder(registradores.get(DX));
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(7, 15);
            var finalValue = new StringBuilder();
            
            if (operand.equalsIgnoreCase(dx.toString())) {
                for (int i = 0; i < ax.toString().toCharArray().length; i++) {
                    finalValue.append(ax.charAt(i) == '1' || dx.charAt(i) == '1' ? '1' : '0');
                }
            } else if (operand.equalsIgnoreCase(ax.toString())) {
                finalValue.append(ax);
            } else
                throw new IllegalArgumentException();
            
            fillRegisterSR(sr, finalValue);
            registradores.replace(SR, sr.reverse().toString());
            registradores.replace(AX, finalValue.toString());
            return registradores;
        }
    },
    OR_REG_OP("00001101"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            return null;
        }
    },
    XOR_REG_REG("00110011"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            var ax = new StringBuilder(registradores.get(AX));
            var dx = new StringBuilder(registradores.get(DX));
            var sr = new StringBuilder(registradores.get(SR)).reverse();
            
            var operand = instrucao.substring(7, 15);
            var finalValue = new StringBuilder();
            
            if (operand.equalsIgnoreCase(dx.toString())) {
                for (int i = 0; i < ax.toString().toCharArray().length; i++) {
                    finalValue.append(ax.charAt(i) == dx.charAt(i) ? '0' : '1');
                }
            } else if (operand.equalsIgnoreCase(ax.toString())) {
                finalValue.append("0".repeat(ax.toString().toCharArray().length));
            } else
                throw new IllegalArgumentException();
            
            fillRegisterSR(sr, finalValue);
            registradores.replace(SR, sr.reverse().toString());
            registradores.replace(AX, finalValue.toString());
            return registradores;
        }
    },
    XOR_REG_OP("00110101"){
        @Override
        public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores) {
            return null;
        }
    };
    private final String valorBinario;

    Mnemonico(String valorBinario) {
        this.valorBinario = valorBinario;
    }
    
    public boolean isEven(String number) {
        return number.split("1").length % 2 == 0;
    }
    public boolean isZero(String number) {
        return !number.contains("1");
    }
    
    public void fillRegisterSR(StringBuilder sr, StringBuilder finalValue) {
        sr.setCharAt(0, '0'); // CF (carry) = 0
        sr.setCharAt(6, isEven(finalValue.toString()) ? '1' : '0'); // PF (parity) = isEven()
        sr.setCharAt(8, isZero(finalValue.toString()) ? '1' : '0'); // ZF (zero) = isZero()
        sr.setCharAt(9, finalValue.charAt(0)); // SF (sign) = most significant bit
        sr.setCharAt(12, '0'); // OF (overflow) = 0
    }
    
    /**
     * 2.Não esquecer de colocar o upcode aqui tbm!
     *
     * @param bytes: bytens em String
     * @return Mnemonico equivalente ao opcode
     */
    public static Mnemonico getByBytes(String bytes) {
        switch (bytes) {
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
            // -------------------------------------------------
            default ->
                    throw new IllegalArgumentException("Não existe mnemônico que equivale a " + bytes);
        }
    }
}
