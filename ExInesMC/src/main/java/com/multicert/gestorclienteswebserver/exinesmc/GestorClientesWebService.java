/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multicert.gestorclienteswebserver.exinesmc;

import com.multicert.exceptions.*;
import com.multicert.service.*;
import com.multicert.database.tables.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * @author aines
 */
@WebService(serviceName = "GestorClientesWebService")
public class GestorClientesWebService {

    Servico s;
    ServiceInterface si;

    public GestorClientesWebService() throws ClassNotFoundException {
        s = new Servico();
        si = s;
    }
//inserir cliente
    @WebMethod(operationName = "AdicionarCliente")
    public void adicionarCliente(@WebParam(name = "cliente") Cliente cliente) throws SoapAPIexception {
        Throwable t;
        try {
            si.adicionarCliente(cliente);

        } catch (ServiceException e) {
            if (e.getMessage().equals("Nome")) {
                t = new IllegalArgumentException("Nome de Cliente invalido");
                throw new SoapAPIexception("Nome de Cliente invalido", t);
            }
            if (e.getMessage().equals("Nif")) {
                t = new IllegalArgumentException("Nif de Cliente invalido");
                throw new SoapAPIexception("Nif de Cliente invalido", t);
            }
            if (e.getMessage().equals("Tipo Cliente")) {
                t = new IllegalArgumentException("Tipo de Cliente invalido");
                throw new SoapAPIexception("Tipo de Cliente invalido", t);
            }
            if (e.getMessage().equals("Endereco")) {
                t = new IllegalArgumentException("Endereco de Cliente invalido");
                throw new SoapAPIexception("Endereco de Cliente invalido", t);
            }
            if (e.getMessage().equals("Cidade")) {
                t = new IllegalArgumentException("Cidade invalida");
                throw new SoapAPIexception("Cidade Invalida", t);
            }
            if (e.getMessage().equals("Telefone")) {
                t = new IllegalArgumentException("Telefone Invalido");
                throw new SoapAPIexception("Telefone Invalido", t);
            }
        } catch (CorrespondenciaNifException e) {
            if (e.getMessage().equals("Telefone")) {
                t = new IllegalArgumentException("Nif do cliente introduzido no telefone nao corresponde ao nif cliente");
                throw new SoapAPIexception("Nif do cliente introduzido no telefone nao corresponde ao nif cliente", t);
            } else {
                t = new IllegalArgumentException("Nif do cliente introduzido no endereco nao corresponde ao nif cliente");
                throw new SoapAPIexception("Nif do cliente introduzido no endereco nao corresponde ao nif cliente", t);
            }
        }
    }

    //remover cliente atraves do seu nif
    public void removerCliente(@WebParam(name = "nif") int nif) throws SoapAPIexception {
        Throwable t;
        try {
            si.eliminarClientePorNif(nif);
        } catch (ServiceException ex) {
            if (ex.getMessage().equals("Nif")) {
                t = new IllegalArgumentException("Nif de Cliente invalido");
                throw new SoapAPIexception("Nif de Cliente invalido", t);
            }
            if (ex.getMessage().equals("Inexistente")) {
                t = new IllegalArgumentException("Nao existem clientes com esse NIF");
                throw new SoapAPIexception("Nao existem clientes com esse NIF", t);
            }
        }
    }

    //listar clientes 
    public List<Cliente> listarClientes() throws SoapAPIexception {
        List<Cliente> temp = null;
        try {
            temp = si.listarClientes();
        } catch (ServiceException ex) {
            Throwable t = new IllegalArgumentException("Nao existem clientes para listar");
            throw new SoapAPIexception("Nao existem clientes para listar", t);

        }

        return temp;
    }
//obter cliente pelo seu nif
    public Cliente obterInfoClientePeloNif(@WebParam(name = "nif") int nif) throws SoapAPIexception {
        Cliente cliente = null;
        Throwable t;
        try {
            cliente = si.obterInfoClientePeloNif(nif);
        } catch (ServiceException ex) {
            if (ex.getMessage().equals("Nif")) {
                t = new IllegalArgumentException("Nif de Cliente invalido");
                throw new SoapAPIexception("Nif de Cliente invalido", t);
            }
            if (ex.getMessage().equals("Inexistente")) {
                t = new IllegalArgumentException("Nao existem clientes com esse NIF");
                throw new SoapAPIexception("Nao existem clientes com esse NIF", t);
            }
        }
        return cliente;
    }
 //obter clientes com um dado nome
    public List<Cliente> obterClientesNome(@WebParam(name = "nome") String nome) throws SoapAPIexception {
        List<Cliente> temp = null;
        Throwable t;
        try {
            temp = si.listarClientesNome(nome);
        } catch (ServiceException ex) {
            if (ex.getMessage().equals("Nome")) {
                t = new IllegalArgumentException("Nome de Cliente invalido");
                throw new SoapAPIexception("Nome de Cliente invalido", t);
            }
            if (ex.getMessage().equals("Inexistente")) {
                t = new IllegalArgumentException("Nao existem clientes com esse nome");
                throw new SoapAPIexception("Nao existem clientes com esse nome", t);
            }
        }
        return temp;
    }

}
