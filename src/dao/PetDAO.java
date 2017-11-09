/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Pet;

/**
 *
 * @author ibrum
 */
public interface PetDAO extends Dao<Pet> {

    public Pet procurarPorNome(String nome);
    
}
