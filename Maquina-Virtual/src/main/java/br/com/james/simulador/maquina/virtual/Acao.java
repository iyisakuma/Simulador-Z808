package br.com.james.simulador.maquina.virtual;

import javax.swing.*;
import java.util.Map;

/**
 * @author yujisakuma
 */
public interface Acao {
    Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console);
    
    default boolean isNumberOfBitsValid(String number, int numberOfBits) {
        return number.length() == numberOfBits;
    }
}
