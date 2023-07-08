package br.com.james.simulador.maquina.virtual;

import java.util.Map;

/**
 *
 * @author yujisakuma
 */
public interface Acao {
    Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores);
    
    boolean isNumberOfBitsValid(String number);
}
