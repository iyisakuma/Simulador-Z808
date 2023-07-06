
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
        var bytes = instrucao.length() / 8;//Quantos bytes tem na instrucao
        var opcode = "";
        switch (bytes) {
            case 2:
                opcode = String.copyValueOf(instrucao.toCharArray(), 0, 16);
                break;
            case 1, 3:
                opcode = String.copyValueOf(instrucao.toCharArray(), 0, 8);
                break;
            default:
                throw new IllegalArgumentException("Instrução não reconhecida.");
        }
        //Retorna Mnemonico associado ao opcode
        return Mnemonico.valueOf(opcode);
    }
}
