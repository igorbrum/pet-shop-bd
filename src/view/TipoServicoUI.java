package view;

import java.util.InputMismatchException;
import java.util.List;
import model.TipoServico;
import negocio.NegocioException;
import negocio.TipoServicoNegocio;
import util.Console;
import view.menu.TipoServicoMenu;

/**
 *
 * @author ibrum
 */
public class TipoServicoUI {
    private TipoServicoNegocio tipoServicoNegocio;

    public TipoServicoUI() {
        this.tipoServicoNegocio = new TipoServicoNegocio();
    }
    
    public void menu(){
        int opcao = -1;
        do {            
            try {
                System.out.println(TipoServicoMenu.getOpcoes());
                opcao = Console.scanInt("Digite uma opcao: ");
                
                switch (opcao) {
                    case TipoServicoMenu.OP_CADASTRAR:
                        cadastrarTipoServico();
                        break;
                    case TipoServicoMenu.OP_DELETAR:
                        deletarTipoServico();
                        break;
                    case TipoServicoMenu.OP_ATUALIZAR:
                        atualizarTipoServico();
                    case TipoServicoMenu.OP_LISTAR:
                        mostrarTipoServico();
                        break;
                    case TipoServicoMenu.OP_SAIR:
                        System.out.println("Voltando...");
                    default:
                        System.out.println("opcao invalida!");
                }
            } catch (InputMismatchException ex) {
                UIUtil.mostrarErro("Opcao invalida");
            }
        } while (opcao != TipoServicoMenu.OP_SAIR);
    }

    private void cadastrarTipoServico() {
        String nome = Console.scanString("Nome: ");
        String tipo = Console.scanString("Tipo de Atendimento: ");
        double preco = Console.scanDouble("Preco: ");
        
        try {
            tipoServicoNegocio.salvar(new TipoServico(nome, tipo, preco));
            System.out.println("Servico " + nome + " cadastrado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void deletarTipoServico() {
        String nome = Console.scanString("Nome do servico a ser deletado: ");
        try {
            TipoServico tipoServico = tipoServicoNegocio.procurarPorNome(nome);
            
            if (UIUtil.getConfirmacao("Deseja realmente excluir essa entrada?")) {
                tipoServicoNegocio.deletar(tipoServico);
                System.out.println("Entrada excluida com sucesso");
            } else {
                System.out.println("Operacao cancelada");
            }
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }

    private void mostrarTipoServico() {
        List<TipoServico> listaTipoServico = tipoServicoNegocio.listar();
        this.mostrarTipoServico(listaTipoServico);
    }

    private void mostrarTipoServico(List<TipoServico> listaTipoServico) {
        if (listaTipoServico.isEmpty()) {
            System.out.println("Tipo de servicos nao encontrados!");
        } else {
            System.out.println("-----------------------------\n");
            System.out.println(String.format("%-30s", "NOME") + "\t"
                    + String.format("%-30s", "|TIPO") + "\t"
                    + String.format("%-30s", "|PRECO"));
            listaTipoServico.forEach((tipoServico) -> {
                System.out.println(String.format("%-30s", tipoServico.getNomeServico()) + "\t"
                        + String.format("%-30s", "|" + tipoServico.getTipoAtendimento()) + "\t"
                        + String.format("%-30s", "|" + tipoServico.getPreco()));
            });
        }
    }

    private void atualizarTipoServico() {
        String nome_servico = Console.scanString("Nome do servico a ser alterado: ");
        try {
            TipoServico tipoServico = tipoServicoNegocio.procurarPorNome(nome_servico);
            this.mostrarTipoServico();

            System.out.println("Digite os dados do cliente que quer alterar [Vazio caso nao queira]");
            String nome = Console.scanString("Nome: ");
            String tipo = Console.scanString("Tipo servico: ");
            if (!nome.isEmpty()) {
                tipoServico.setNomeServico(nome);
            }
            if (!tipo.isEmpty()) {
                tipoServico.setTipoAtendimento(tipo);
            }
            tipoServicoNegocio.atualizar(tipoServico);
            System.out.println("Servico " + nome + " atualizado com sucesso!");
        } catch (NegocioException ex) {
            UIUtil.mostrarErro(ex.getMessage());
        }
    }
}
