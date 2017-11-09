package dao.db;

import dao.TipoServicoDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.TipoServico;

/**
 *
 * @author ibrum
 */
public class TipoServicoDAOBD extends DAOBD<TipoServico> implements TipoServicoDAO{

    @Override
    public void salvar(TipoServico dominio) {
        int id = 0;
        try {
            String sql = "INSERT INTO tipos_servicos (nome, tipo_atendimento, preco)"
                        +" VALUES (?, ?, ?)";
            
            conectarObtendoID(sql);
            
            comando.setString(1, dominio.getNomeServico());
            comando.setString(2, dominio.getTipoAtendimento());
            comando.setDouble(3, dominio.getPreco());
            
            comando.executeUpdate();
            
            ResultSet resultado = comando.getGeneratedKeys();
            if (resultado.next()) {
                id = resultado.getInt(1);
                dominio.setId(id);
            } else {
                System.err.println("Erro de Sistema - Nao gerou o id conforme esperado!");
                throw new BDException("Nao gerou o id conforme esperado!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao salvar tipo de servico no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public void deletar(TipoServico dominio) {
        try {
            String sql = "DELETE FROM tipos_servicos WHERE id = ?";
            
            conectar(sql);
            comando.setInt(1, dominio.getId());
            comando.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao deletar cliente no Banco de Dados!");
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void atualizar(TipoServico dominio) {
        try {
            String sql = "UPDATE tipos_servicos SET nome=?, tipo_atendimento=?, preco=? "
                    + "WHERE id=?";

            conectar(sql);
            comando.setString(1, dominio.getNomeServico());
            comando.setString(2, dominio.getTipoAtendimento());
            comando.setDouble(3, dominio.getPreco());
            comando.setInt(4, dominio.getId());
            comando.executeUpdate();

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao atualizar o tipo de servico no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<TipoServico> listar() {
        List<TipoServico> listaTipoServicos = new ArrayList<>();
        
        String sql = "SELECT * FROM tipos_servicos";
        
        try {
            conectar(sql);
            
            ResultSet resultado = comando.executeQuery();
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nome = resultado.getString("nome");
                String tipo = resultado.getString("tipo_atendimento");
                double preco = resultado.getDouble("preco");
                
                TipoServico tipoServico = new TipoServico(id, nome, tipo, preco);
                listaTipoServicos.add(tipoServico);
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar os tipos de servicos do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }
        return (listaTipoServicos);
    }

    @Override
    public TipoServico procurarPorId(int id) {
        String sql = "SELECT * FROM tipos_servicos WHERE id = ?";
        
        try {
            conectar(sql);
            comando.setInt(1, id);

            ResultSet resultado = comando.executeQuery();

            if (resultado.next()) {
                String nome = resultado.getString("nome");
                String tipo = resultado.getString("tipo_atendimento");
                double preco = resultado.getDouble("preco");
                
                TipoServico tipoServico = new TipoServico(id, nome, tipo, preco);

                return tipoServico;
            }

        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o tipo de servico pelo id do Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }

    @Override
    public TipoServico procurarPorNome(String nome) {
        String sql = "SELECT * FROM tipos_servicos WHERE nome = ?";
        
        try {
            conectar(sql);
            comando.setString(1, nome);
            
            ResultSet resultado = comando.executeQuery();
            
            if (resultado.next()) {
                int id = resultado.getInt("id");
                String tipo = resultado.getString("tipo_atendimento");
                double preco = resultado.getDouble("preco");
                TipoServico tipoServico = new TipoServico(id, nome, tipo, preco);
                return tipoServico;
            }
        } catch (SQLException ex) {
            System.err.println("Erro de Sistema - Problema ao buscar o tipo de servico pelo nome no Banco de Dados!");
            throw new BDException(ex);
        } finally {
            fecharConexao();
        }

        return (null);
    }
    
}
