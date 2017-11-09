package view;

import util.Console;



/**
 *
 * @author ibrum
 */
public class UIUtil {
    public static final String SIM = "sim";
    public static final String NAO = "nao";
    
    public static void mostrarErro(Object msgErro){
        System.out.println(msgErro);
    }
    
    public static boolean getConfirmacao(Object msg){
        String confirmacao = "NAO";
        do {            
            confirmacao = Console.scanString(msg);
            if (confirmacao.equalsIgnoreCase(SIM)) {
                return true;
            } else if (confirmacao.equalsIgnoreCase(NAO)){
                return false;
            } else {
                System.out.println("Opcao invalida");
            }
        } while (confirmacao.equalsIgnoreCase(SIM) || confirmacao.equalsIgnoreCase(NAO));
        return false;
    }
}
