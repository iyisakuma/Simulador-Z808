package br.com.james.simulador.maquina.virtual;

import java.util.Stack;

public class PilhaDoSistema {
    
    private final Stack<String> stack = new Stack<>();
    
    public boolean isSizeUnderLimit() {
        return this.stack.capacity() <= 12;
    }
    
    public String pushEndereco(String endereco) {
        return this.stack.push(endereco);
    }
    
    public String popEndereco() {
        return this.stack.pop();
    }
    
    public String peekEndereco() {
        return this.stack.peek();
    }
    
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
}
