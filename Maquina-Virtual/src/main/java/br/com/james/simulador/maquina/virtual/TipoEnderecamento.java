package br.com.james.simulador.maquina.virtual;

/**
 *
 * @author yujisakuma
 */
public enum TipoEnderecamento {
    DIRETO('0'), IMEDIATO('1');
    
    private char bit;
    
    private TipoEnderecamento(char bit){
        this.bit = bit;
    }
    
    public static TipoEnderecamento getByBit(char bit){
        switch (bit) {
            case '0':
                return DIRETO;
            case '1':
                return IMEDIATO;
            default:
                throw new IllegalArgumentException("N");
        }
    }
}
