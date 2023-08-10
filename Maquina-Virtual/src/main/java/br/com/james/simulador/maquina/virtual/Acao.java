package br.com.james.simulador.maquina.virtual;

import java.util.Map;
import javax.swing.JTextArea;

/**
 *
 * @author yujisakuma
 */
public interface Acao {
    Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores, JTextArea console);
    
    boolean  isNumberOfBitsValid(String number);
}
