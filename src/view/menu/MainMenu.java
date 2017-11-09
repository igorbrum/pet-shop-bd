package view.menu;

/**
 *
 * @author ibrum
 */
public class MainMenu {
    public static final int OP_SAIR = 0;
    public static final int OP_CLIENTES = 1;
    public static final int OP_PETS = 2;
    public static final int OP_SERVICOS = 3;
    public static final int OP_VENDAS = 4;
    
    public static String getOpcoes() {
        return("\n===== MENU PRINCIPAL =====\n\n"
                + "1 - Area de Clientes\n"
                + "2 - Area de Pets\n"
                + "3 - Area de Servicos\n"
                + "4 - Area de Vendas\n"
                + "0 - Sair da Aplicação\n"
                + "\n==========================");
    }
    
}
