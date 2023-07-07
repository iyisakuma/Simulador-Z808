
package br.com.james.simulador.maquina.virtual;

import java.util.Map;


/**
 *
 * @author yujisakuma
 */
public class Executor {
    
    public static void run(String instrucao, Map<RegistradorEnum, String> registradores){
        getMnemonico(instrucao).acao(instrucao, registradores);
    }
    
    private static Mnemonico getMnemonico(String instrucao){
        var opcode = String.copyValueOf(instrucao.toCharArray(), 0, 8);
        //Retorna Mnemonico associado ao opcode
        return Mnemonico.getByBytes(opcode);
    }
}
