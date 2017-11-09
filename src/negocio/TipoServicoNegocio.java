package negocio;

import dao.TipoServicoDAO;
import dao.db.TipoServicoDAOBD;
import java.util.List;
import model.TipoServico;

/**
 *
 * @author ibrum
 */
public class TipoServicoNegocio {
    private TipoServicoDAO tipoServicoDAO;
    
    public TipoServicoNegocio(){
        this.tipoServicoDAO = new TipoServicoDAOBD();
    }
    
    public void salvar(TipoServico tipoServico) throws NegocioException {
        this.validarCamposObrigatorios(tipoServico);
        this.tipoServicoDAO.salvar(tipoServico);
    }
    
    public List<TipoServico> listar(){
        return (tipoServicoDAO.listar());
    }
    
    public void deletar(TipoServico tipoServico) throws NegocioException{
        if (tipoServico == null || tipoServico.getNomeServico() == null) {
            throw new NegocioException("Tipo de servico nao existe!");
        }
        this.tipoServicoDAO.deletar(tipoServico);
    }
    
    public void atualizar(TipoServico tipoServico) throws NegocioException{
        if (tipoServico == null || tipoServico.getNomeServico() == null) {
            throw new NegocioException("Tipo de servico nao existe!");
        }
        this.tipoServicoDAO.atualizar(tipoServico);
    }

    private void validarCamposObrigatorios(TipoServico tipoServico) throws NegocioException {
        if (tipoServico.getNomeServico() == null || tipoServico.getNomeServico().isEmpty()) {
            throw new NegocioException("Nome não informado!");
        }
    }

    public TipoServico procurarPorNome(String nome) throws NegocioException {
        if (nome == null || nome.isEmpty()) {
            throw new NegocioException("Nome nao informado");
        }
        System.out.println(nome);
        TipoServico tipoServico = tipoServicoDAO.procurarPorNome(nome);
        
        if (tipoServico == null) {
            throw new NegocioException("Tipo de Atendimento nao encontrado!");
        }
        return (tipoServico);
    }
}
