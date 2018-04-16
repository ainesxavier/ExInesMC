/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multicert.service;

import com.multicert.database.tables.*;
import com.multicert.exceptions.CorrespondenciaNifException;
import com.multicert.exceptions.ServiceException;

import java.util.List;

/**
 *
 * @author aines
 */
public interface ServiceInterface {

    void adicionarCliente(Cliente c) throws ServiceException, CorrespondenciaNifException;

    void adicionarClienteMorada(Cliente c, Endereco m);

    void adicionarClienteContacto(Cliente c, Contacto cont);

    void adicionarClienteMoradaContacto(Cliente c, Endereco m, Contacto cont);

    void adicionarMoradaAoCliente(int nif, Endereco m);

    void adicionarContactoAoCliente(int nif, Contacto cont);

    void eliminarCliente(Cliente c);

    boolean eliminarClientePorNif(int nif) throws ServiceException;

    void eliminarMoradaClientePorEndereco(int nif, String enderecoMorada);

    void eliminarContactoClientePorEndereco(int nif, int telefone);

    List<Cliente> listarClientes() throws ServiceException;

    List<Cliente> listarClientesNome(String nome) throws ServiceException;

    public Cliente obterInfoClientePeloNif(int nif) throws ServiceException;

}
