package br.com.james.simulador.maquina.virtual;

import java.util.Map;
import static br.com.james.simulador.maquina.virtual.RegistradorEnum.*;

/**
 *
 * @author yujisakuma
 */
public enum Mnemonico implements Acao {
    /**
     * 1. Quando adicionar um mnemonico adicionar o seu upcode em binario.
     * Certificar que tenha 1 bytes ou 2 bytes (Depende da instrucao). Além
     * disso, implementar o metodo acao para cada Mnemonico novo.
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
    };
    private final String valorBinario;

    private Mnemonico(String valorBinario) {
        this.valorBinario = valorBinario;
    }

    /**
     * 2.Não esquecer de colocar o upcode aqui tbm!
     *
     * @param bytes: bytens em String
     * @return Mnemonico equivalente ao opcode
     */
    public static Mnemonico getByBytes(String bytes) {
        switch (bytes) {
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
            default ->
                throw new IllegalArgumentException("Não existe mnmônico equivale a " + bytes);
        }
    }
}
