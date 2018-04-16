/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multicert.database.tables;

/**
 *
 * @author aines
 */

public class Cliente {
    private int nif;
    private String nome;
    private TipoDeCliente tipo_cliente;
    Endereco endereco;
    Contacto contacto;
  
    public Cliente(TipoDeCliente tipo_cliente, String nome, int nif, Endereco endereco, Contacto contacto){

        this.nome=nome;
        this.nif=nif;
        this.tipo_cliente=tipo_cliente;
        this.endereco = endereco;
        this.contacto = contacto;
    }

    public Cliente() {
        this.nome="";
        this.nif=0;
        this.tipo_cliente=null;
        this.endereco = null;
        this.contacto = null;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int nif) {
        this.nif = nif;
    }

    public TipoDeCliente getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(TipoDeCliente tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    @Override
    public String toString() {
        return "nif=" + nif + ", nome=" + nome + ", tipo_cliente=" + tipo_cliente + ", endereco=" + endereco + ", contacto=" + contacto + '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); //To change body of generated methods, choose Tools | Templates.
    }


}

