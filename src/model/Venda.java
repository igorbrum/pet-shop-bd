package model;

import util.DateUtil;

/**
 *
 * @author ibrum
 */
public class Venda {
    private int id;
    private Cliente cliente;
    private Pet pet;
    private TipoServico tipoServico;
    private DateUtil data;
    private double valor;

    public Venda(Cliente cliente, Pet pet, TipoServico tipoServico, DateUtil data, double valor) {
        this.cliente = cliente;
        this.pet = pet;
        this.tipoServico = tipoServico;
        this.data = data;
    }
    
    public Venda(int id, Cliente cliente, Pet pet, TipoServico tipoServico, DateUtil data, double valor) {
        this.id = id;
        this.cliente = cliente;
        this.pet = pet;
        this.tipoServico = tipoServico;
        this.data = data;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public Cliente getCliente() {
        return cliente;
    }

    public Pet getPet() {
        return pet;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public DateUtil getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }
    
    
}
