package br.com.james.simulador.maquina.virtual;

import java.util.Date;
import java.util.Map;
import javax.swing.JTextArea;

/**
 *
 * @author yujisakuma
 */
public class Executor {

    private JTextArea console;

    public Executor(JTextArea console) {
        this.console = console;
    }

    public void run(String instrucao, Map<RegistradorEnum, String> registradores) {
        String text = console.getText();
        console.setText(text + "\n" + String.format("[%s]Execuntando o programa", new Date().toString()));
        atualizaEndereco(registradores);
        getMnemonico(instrucao).acao(instrucao, registradores, console);
    }

    private static Mnemonico getMnemonico(String instrucao) {
        var opcode = instrucao.substring(0, 8);
        return Mnemonico.getByBytes(opcode);
    }

    private static void atualizaEndereco(Map<RegistradorEnum, String> registradores) {
        var enderecoBinario = registradores.get(RegistradorEnum.IP);
        var enderecoDecimal = Util.binarioParaDecimal(enderecoBinario);
        enderecoDecimal++;
        enderecoBinario = String.format("%16s", Integer.toBinaryString(enderecoDecimal)).replaceAll(" ", "0");
        registradores.replace(RegistradorEnum.IP, enderecoBinario);
    }
}
