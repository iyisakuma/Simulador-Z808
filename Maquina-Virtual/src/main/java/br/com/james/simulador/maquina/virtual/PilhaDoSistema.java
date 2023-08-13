package br.com.james.simulador.maquina.virtual;

import java.util.Stack;

public class PilhaDoSistema {
    
    private final Stack<String> stack = new Stack<>();
    
    public boolean isSizeUnderLimit() {
        return this.stack.capacity() <= 12;
    }
    
    public void push(String str) {
        if (str.length() != 16)
            throw new IllegalArgumentException("Número incorreto de bits!");
        this.stack.push(str);
    }
    
    public String pop() {
        return this.stack.pop();
    }
    
    public String peek() {
        return this.stack.peek();
    }
    
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }
}
