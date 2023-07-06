package br.com.james.simulador.maquina.virtual;

import java.util.Map;

/**
 *
 * @author yujisakuma
 */
public interface Acao {
    public Map<RegistradorEnum, String> acao(String instrucao, Map<RegistradorEnum, String> registradores);
}
