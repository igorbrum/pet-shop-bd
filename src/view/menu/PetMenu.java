package view.menu;

/**
 *
 * @author ibrum
 */
public class PetMenu {
    public static final int OP_CADASTRAR = 1;
    public static final int OP_DELETAR = 2;
    public static final int OP_ATUALIZAR = 3;
    public static final int OP_LISTAR = 4;
    public static final int OP_CONSULTAR = 5;
    public static final int OP_SAIR = 0;
    
    public static String getOpcoes() {
        return ("n===== MENU DE PETS =====\n"
                + "1- Cadastrar Pets\n"
                + "2- Deletar Pet\n"
                + "3- Atualizar dados do Pet\n"
                + "4- Listar Pet\n"
                + "5- Consultar Pet por Nome\n"
                + "0- Sair"
                + "\n==============================");
    }
}
