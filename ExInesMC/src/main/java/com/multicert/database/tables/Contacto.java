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
public class Contacto {

    private short indexContacto;
    private int telefone;
    private int nifCliente;

    public Contacto(short index, int telefone, int nifCliente) {
        this.indexContacto = index;
        this.telefone = telefone;
        this.nifCliente = nifCliente;
    }

    public Contacto() {
        this.indexContacto = 0;
        this.telefone = 0;
        this.nifCliente = 0;
    }

    public short getIndexContacto() {
        return indexContacto;
    }

    public void setIndexContacto(short indexContacto) {
        this.indexContacto = indexContacto;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public int getNifCliente() {
        return nifCliente;
    }

    public void setNifCliente(int nifCliente) {
        this.nifCliente = nifCliente;
    }

    @Override
    public String toString() {
        return "Contacto{" + "indexContacto=" + indexContacto + ", telefone=" + telefone + ", nifCliente=" + nifCliente + '}';
    }

}
