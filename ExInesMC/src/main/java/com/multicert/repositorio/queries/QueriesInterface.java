/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multicert.repositorio.queries;

import com.multicert.database.tables.*;
import java.util.List;

/**
 *
 * @author aines
 */
public interface QueriesInterface {

    //adicionar 1 novo cliente
     void adicionarClienteBD(Cliente cliente);

    //adicionar endereco cliente
     void adicionarMoradaBD(Endereco endereco);

    //add contacto
     void adicionarContactoBD(Contacto contacto);

    boolean removerMoradasCliente(int nif);

    void removerMoradaClientePorEndereco(int nif, String endereco);

    boolean removerContactosCliente(int nif);

    void removerContactoClientePorTelefone(int nif, int telefone);

     List<Cliente> listarClientes();
    
    List<Cliente> listarClientesNome(String nome);

    boolean removerCliente(int nif);
    
    Endereco getMorada(int cod_cliente);

    Contacto getContacto(int cod_cliente);
    
     Cliente getClienteNif(int nif);
}
