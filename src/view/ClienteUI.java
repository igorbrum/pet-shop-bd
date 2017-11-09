package view;

import java.util.InputMismatchException;
import java.util.List;
import model.Cliente;
import negocio.ClienteNegocio;
import negocio.NegocioException;
import util.Console;
import view.menu.ClienteMenu;

/**
 *
 * @author ibrum
 */
public class ClienteUI {
    private ClienteNegocio clienteNegocio;
    
    public ClienteUI(){
        this.clienteNegocio = new ClienteNegocio();
    }
    
    public void menu() {
        int opcao = -1;
        do {            
            try {
                System.out.println(ClienteMenu.getOpcoes());
                opcao = Console.scanInt("Digite uma opcao: ");
                switch (opcao) {
                    case ClienteMenu.OP_CADASTRAR:
                        cadastrarCliente();
                        break;
                    case ClienteMenu.OP_DELETAR:
                        deletarCliente();
                        break;
                    case ClienteMenu.OP_ATUALIZAR:
                        atualizarCliente();
                        break;
                    case ClienteMenu.OP_LISTAR:
                        mostrarClientes();
                        break;
                    case ClienteMenu.OP_CONSULTAR:
                        consultarClientesPorNome();
                        break;
                    case ClienteMenu.OP_SAIR:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("opcao invalida!");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Opcao invalida");
            }
        } while (opcao != ClienteMenu.OP_SAIR);
    }
    
    private void cadastrarCliente() {
        String rg = Console.scanString("RG: ");
        String nome = Console.scanString("Nome: ");
        String telefone = Console.scanString("Telefone: ");
        try {
            clienteNegocio.salvar(new Cliente(rg, nome, telefone));
            System.out.println("Cliente " + nome + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    public void mostrarClientes() {
        List<Cliente> listaClientes = clienteNegocio.listar();
        this.mostrarClientes(listaClientes);
    }
    
    private void deletarCliente() {
        String rg = Console.scanString("RG do cliente a ser deletado: ");
        try {
            Cliente c = clienteNegocio.procurarPorRG(rg);
            this.mostrarClientes();
            if (UIUtil.getConfirmacao("Realmente deseja excluir esse cliente?")) {
                clienteNegocio.deletar(c);
                System.out.println("Cliente deletado com sucesso!");
            } else {
                System.out.println("Operacao cancelada!");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
    
    private void atualizarCliente() {
        String rg = Console.scanString("RG do cliente a ser alterado: ");
        try {
            Cliente c = clienteNegocio.procurarPorRG(rg);
            this.mostrarClientes();

            System.out.println("Digite os dados do cliente que quer alterar [Vazio caso nao queira]");
            String nome = Console.scanString("Nome: ");
            String telefone = Console.scanString("Telefone: ");
            if (!nome.isEmpty()) {
                c.setNome(nome);
            }
            if (!telefone.isEmpty()) {
                c.setTelefone(telefone);
            }
            clienteNegocio.atualizar(c);
            System.out.println("Cliente " + nome + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        } 
    }
    
    private void consultarClientesPorNome() {
        String nome = Console.scanString("Nome: ");
        try {
            List<Cliente> listaCliente = clienteNegocio.listarPorNome(nome);
            this.mostrarClientes(listaCliente);
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }

    }
    
    private void mostrarCliente(Cliente c) {
        System.out.println("-----------------------------");
        System.out.println("Cliente");
        System.out.println("RG: " + c.getRg());
        System.out.println("Nome: " + c.getNome());
        System.out.println("Telefone: " + c.getTelefone());
        System.out.println("-----------------------------");
    }
    
    private void mostrarClientes(List<Cliente> listaCliente) {
        if (listaCliente.isEmpty()) {
            System.out.println("Clientes nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-10s", "RG") + "\t"
                    + String.format("%-20s", "|NOME") + "\t"
                    + String.format("%-20s", "|TELEFONE"));
            listaCliente.forEach((cliente) -> {
                System.out.println(String.format("%-10s", cliente.getRg()) + "\t"
                        + String.format("%-20s", "|" + cliente.getNome()) + "\t"
                        + String.format("%-20s", "|" + cliente.getTelefone()));
            });
        }
    }
}
