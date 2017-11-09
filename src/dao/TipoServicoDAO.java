/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.TipoServico;

/**
 *
 * @author ibrum
 */
public interface TipoServicoDAO extends Dao<TipoServico>{
    public TipoServico procurarPorNome(String nome);
}
