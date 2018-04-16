/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multicert.service;

import com.multicert.database.tables.*;
import com.multicert.exceptions.CorrespondenciaNifException;
import com.multicert.exceptions.ServiceException;
import com.multicert.repositorio.queries.*;

import java.util.List;

/**
 * @author aines
 */
public class Servico implements ServiceInterface {

    Queries queries;
    QueriesInterface qint;

    public Servico() throws ClassNotFoundException {
        queries = new Queries();
        qint = queries;
    }

    @Override
    public void adicionarCliente(Cliente c) throws ServiceException, CorrespondenciaNifException {
        System.out.println("\n aqui estou no adicionar cliente do servico e o cliente e: " + c.toString());
        //verifica nome       
        if (c.getNome() == null || c.getNome().isEmpty() || !c.getNome().matches("^[ A-Za-z]+$")) {
            throw new ServiceException("Nome");
        }

        //verifica nif
        if (c.getNif() == 0 || String.valueOf(c.getNif()).length() != 9 || !String.valueOf(c.getNif()).matches("[0-9]+")) {
            throw new ServiceException("Nif");
        }

        //verifica enum (tipo cliente)
        //!c.getTipo_cliente().toString().equals("EMPRESA") && !c.getTipo_cliente().toString().equals("PESSOA")
        if (c.getTipo_cliente() == null) {
            // System.out.println("este e o tipo de cliente: "+c.getTipo_cliente().name());
            throw new ServiceException("Tipo Cliente");
        }
        //verifica endereco
        if (c.getEndereco().getEnderecoMorada() == null || c.getEndereco().getEnderecoMorada().isEmpty()) {
            throw new ServiceException("Endereco");
        }
        //verifica cidade
        if (c.getEndereco().getCidade() == null || c.getEndereco().getCidade().isEmpty() || !c.getEndereco().getCidade().matches("^[ A-Za-z]+$")) {
            throw new ServiceException("Cidade");
        }
        //verifica telefone
        if (c.getContacto().getTelefone() == 0 || String.valueOf(c.getContacto().getTelefone()).length() != 9 || !String.valueOf(c.getContacto().getTelefone()).matches("[0-9]+")) {
            throw new ServiceException("Telefone");
        }
        //verifica se nifs coincidem 		
        if (c.getContacto().getNifCliente() != c.getNif()) {
            throw new CorrespondenciaNifException("Telefone");
        }
        //verifica se nifs coincidem 
        if (c.getEndereco().getNifCliente() != c.getNif()) {
            throw new CorrespondenciaNifException("Endereço");
        }

        System.out.println("\ncliente no serviço esta a passar: "+c.toString());
        qint.adicionarClienteBD(c);

    }

    @Override
    public void adicionarClienteMorada(Cliente c, Endereco m) {
        qint.adicionarClienteBD(c);
        m.setNifCliente(c.getNif());
        qint.adicionarMoradaBD(m);

    }

    @Override
    public void adicionarClienteContacto(Cliente c, Contacto cont) {
        qint.adicionarClienteBD(c);
        cont.setNifCliente(c.getNif());
        qint.adicionarContactoBD(cont);

    }

    @Override
    public void adicionarClienteMoradaContacto(Cliente c, Endereco m, Contacto cont) {
        qint.adicionarClienteBD(c);
        m.setNifCliente(c.getNif());
        cont.setNifCliente(c.getNif());
        qint.adicionarMoradaBD(m);
        qint.adicionarContactoBD(cont);

    }

    @Override
    public void adicionarMoradaAoCliente(int nif, Endereco m) {
        m.setNifCliente(nif);
        qint.adicionarMoradaBD(m);

    }

    @Override
    public void adicionarContactoAoCliente(int nif, Contacto cont) {

        cont.setNifCliente(nif);
        qint.adicionarContactoBD(cont);

    }

    @Override
    public void eliminarCliente(Cliente c) {
        qint.removerMoradasCliente(c.getNif());
        qint.removerContactosCliente(c.getNif());
        qint.removerCliente(c.getNif());
    }

    @Override
    public boolean eliminarClientePorNif(int nif) throws ServiceException {
        boolean moradas = false;
        boolean cliente = false;
        boolean contactos = false;
        boolean temp = false;

        //verifica nif
        if (nif == 0 || String.valueOf(nif).length() != 9 || !String.valueOf(nif).matches("[0-9]+")) {
            throw new ServiceException("Nif");
        }
        moradas = qint.removerMoradasCliente(nif);
        contactos = qint.removerContactosCliente(nif);
        cliente = qint.removerCliente(nif);
        if (moradas == true && contactos == true && cliente == true) {
            System.out.println("\n \nmoradas" + moradas + "cliente" + cliente + "contactos" + contactos);
            temp = true;
        }
        else{
            throw new ServiceException("Inexistente");
        }

        return temp;
    }

    @Override
    public void eliminarMoradaClientePorEndereco(int nif, String enderecoMorada) {
        qint.removerMoradaClientePorEndereco(nif, enderecoMorada);
    }

    @Override
    public void eliminarContactoClientePorEndereco(int nif, int telefone) {
        qint.removerContactoClientePorTelefone(nif, telefone);
    }

    @Override
    public List<Cliente> listarClientes() throws ServiceException {
        List<Cliente> temp = null;
        temp = qint.listarClientes();
        if(temp.isEmpty()){
            throw new ServiceException("Inexistente");
        }
        return temp;
    }

    @Override
    public List<Cliente> listarClientesNome(String nome) throws ServiceException {
        List<Cliente> temp = null;
        //verifica nome       
        if (nome == null || nome.isEmpty() || !nome.matches("^[ A-Za-z]+$")) {
            throw new ServiceException("Nome");
        }
        temp = qint.listarClientesNome(nome);
        if(temp.isEmpty()){
            throw new ServiceException("Inexistente");
        }
        return temp;
    }

    @Override
    public Cliente obterInfoClientePeloNif(int nif) throws ServiceException {
        Cliente temp=null;
        //verifica nif
        if (nif == 0 || String.valueOf(nif).length() != 9 || !String.valueOf(nif).matches("[0-9]+")) {
            throw new ServiceException("Nif");
        }
        temp = qint.getClienteNif(nif);
        if(temp==null){
            throw new ServiceException("Inexistente");
        }
        return temp;
    }

}
