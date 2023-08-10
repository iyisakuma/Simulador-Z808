package br.com.james.simulador.maquina.virtual;

/**
 * @author yujisakuma
 */
public enum RegistradorEnum {
    AX("11000000"),
    CL(""),
    DX("11000010"),
    RI(""),
    REM(""),
    RBM(""),
    SP(""),
    SI(""),
    IP(""),
    SR(""),
    END("");
    
    private final String endereco;
    
    RegistradorEnum(String endereco) {
        this.endereco = endereco;
    }
    
    public String getEndereco() {
        return endereco;
    }
}
