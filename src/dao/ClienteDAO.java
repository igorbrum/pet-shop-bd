package dao;

import java.util.List;
import model.Cliente;

/**
 *
 * @author ibrum
 */
public interface ClienteDAO extends Dao<Cliente>{
    public Cliente procurarPorRG(String rg);
    public List<Cliente> listarPorNome(String nome);
}
