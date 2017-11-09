package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;
import model.Pet;
import negocio.ClienteNegocio;
import negocio.NegocioException;
import negocio.PetNegocio;
import util.Console;
import view.menu.PetMenu;

/**
 *
 * @author ibrum
 */
public class PetUI {
    private PetNegocio petNegocio;
    private ClienteNegocio clienteNegocio;
    
    public PetUI(){
        this.petNegocio = new PetNegocio();
        this.clienteNegocio = new ClienteNegocio();
    }
    
    public void menu() {
        int opcao = -1;
        do {            
            try {
                System.out.println(PetMenu.getOpcoes());
                opcao = Console.scanInt("Digite uma opcao: ");
                switch (opcao) {
                    case PetMenu.OP_CADASTRAR:
                        cadastrarPet();
                        break;
                    case PetMenu.OP_DELETAR:
                        deletarPet();
                        break;
                    case PetMenu.OP_ATUALIZAR:
                        atualizarPet();
                        break;
                    case PetMenu.OP_LISTAR:
                        mostrarPets();
                        break;
                    case PetMenu.OP_CONSULTAR:
                        consultarPetsPorNome();
                        break;
                    case PetMenu.OP_SAIR:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("opcao invalida!");
                        break;
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Opcao invalida");
            }
        } while (opcao != PetMenu.OP_SAIR);
    }

    private void cadastrarPet() {
        System.out.println("==========");
        Cliente cliente = null;
        
        do {            
            new ClienteUI().mostrarClientes();
            String rg = Console.scanString("Selecione, através do RG, o cliente: ");
            try {
                cliente = clienteNegocio.procurarPorRG(rg);
            } catch (NegocioException ex) {
                Logger.getLogger(PetUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (cliente == null) {
                System.err.println("RG invalido ou nao encontrado.");
            }
        } while (cliente == null);
        
        String nome = Console.scanString("Nome: ");
        String tipo = Console.scanString("Tipo: ");
        
        try {
            petNegocio.salvar(new Pet(nome, tipo, cliente));
            System.out.println("Pet "+ nome + " cadastrado com sucesso");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void deletarPet() {
        String nome = Console.scanString("Nome do pet a ser deletado: ");
        try {
            Pet pet = petNegocio.procurarPorNome(nome);
            if (UIUtil.getConfirmacao("Deseja realmente excluir essa entrada?")) {
                petNegocio.deletar(pet);
                System.out.println("Entrada excluida com sucesso");
            } else {
                System.out.println("Operacao cancelada");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void atualizarPet() {
        String nome_pet = Console.scanString("Nome do pet a ser alterado: ");
        try {
            Pet p = petNegocio.procurarPorNome(nome_pet);
            this.mostrarPets();
            
            System.out.println("Digite os dados do pet que quer alterar [Vazio caso nao queira]");
            String nome = Console.scanString("Nome: ");
            String tipo = Console.scanString("Tipo: ");
            if (!nome.isEmpty()) {
                p.setNome(nome);
            }
            if (!tipo.isEmpty()) {
                p.setTipo(tipo);
            }
            petNegocio.atualizar(p);
            System.out.println("Pet " + nome + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void mostrarPets() {
        List<Pet> listaPet = petNegocio.listar();
        this.mostrarPets(listaPet);
    }
    
    private void mostrarPets(List<Pet> listaPet) {
        if (listaPet.isEmpty()) {
            System.out.println("Pet nao encontrado");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-30s", "DONO") + "\t"
                    + String.format("%-30s", "|NOME") + "\t"
                    + String.format("%-30s", "|TIPO"));
            listaPet.forEach((pet) -> {
                System.out.println(String.format("%-30s", pet.getNome()) + "\t"
                        + String.format("%-30s", "|" + pet.getNome()) + "\t"
                        + String.format("%-30s", "|" + pet.getTipo()));
            });
        }
    }

    private void consultarPetsPorNome() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
