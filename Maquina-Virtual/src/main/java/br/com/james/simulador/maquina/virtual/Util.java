
package br.com.james.simulador.maquina.virtual;

/**
 *
 * @author yujisakuma
 */
public class Util {
        
    public static int binarioParaDecimal(String binario) {
        var somador = 0;
        var expoente = 0;
        for(int i = binario.toCharArray().length-1; i>=0; i--){
            if(binario.toCharArray()[i] == '1'){
                somador += Math.pow(2, expoente);
            }
            expoente++;
        }
        return somador;
    }
}
