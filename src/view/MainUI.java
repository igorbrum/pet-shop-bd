package view;

import negocio.ClienteNegocio;
import util.Console;
import view.menu.MainMenu;

/**
 *
 * @author ibrum
 */
public class MainUI {
    private ClienteNegocio clienteNegocio;
    
    public MainUI(){
        this.clienteNegocio = new ClienteNegocio();
    }
    
    public void menu() {
        int opcao = -1;
        do {            
            try {
                System.out.println(MainMenu.getOpcoes());
                opcao = Console.scanInt("Digite sua opção: ");
                System.out.println("-------------------------------------");
                
                switch(opcao){
                    case MainMenu.OP_CLIENTES:
                        new ClienteUI().menu();
                        break;
                    case MainMenu.OP_SERVICOS:
                        new TipoServicoUI().menu();
                        break;
                    case MainMenu.OP_PETS:
                        new PetUI().menu();
                }
            } catch (Exception e) {
            }
        } while (opcao != MainMenu.OP_SAIR);
    }
    
}
