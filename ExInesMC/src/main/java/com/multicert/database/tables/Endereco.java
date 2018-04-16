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
public class Endereco {

    private short indexMorada;
    private String enderecoMorada;
    private String cidade;
    private int nifCliente;

    public Endereco(short indexMorada, String enderecoMorada, String cidade, int nifCliente) {
        this.indexMorada = indexMorada;
        this.enderecoMorada = enderecoMorada;
        this.cidade = cidade;
        this.nifCliente = nifCliente;
    }

    public Endereco(){
     this.indexMorada = 0;
        this.enderecoMorada = "";
        this.cidade = "";
        this.nifCliente = 0;
    }
    
    public short getIndexMorada() {
        return indexMorada;
    }

    public void setIndexMorada(short indexMorada) {
        this.indexMorada = indexMorada;
    }

    public String getEnderecoMorada() {
        return enderecoMorada;
    }

    public void setEnderecoMorada(String enderecoMorada) {
        this.enderecoMorada = enderecoMorada;
    }
    public int getNifCliente() {
        return nifCliente;
    }

    public void setNifCliente(int nifCliente) {
        this.nifCliente = nifCliente;
    }


    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereco{" + "indexMorada=" + indexMorada + ", endereco=" + enderecoMorada + ", cidade=" + cidade + ", cliente=" + nifCliente + '}';
    }

}
