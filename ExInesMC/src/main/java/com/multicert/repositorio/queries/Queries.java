/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multicert.repositorio.queries;

import com.multicert.db.connection.DatabaseConnection;
import com.multicert.database.tables.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aines
 */
public class Queries implements QueriesInterface {

    Connection con;

    public Queries() {
        this.con = new DatabaseConnection().dbConnection();
    }

    @Override
    public void adicionarClienteBD(Cliente cliente) {
        String query = null;
        PreparedStatement pstmt = null;
        System.out.println("cheguei a query \n \n Cliente: " + cliente.toString());
        query = "INSERT INTO CLIENTE (TIPO_CLIENTE, NOME, NIF) VALUES(?, ?, ?)";
        System.out.println("query \n \n" + query);
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, cliente.getTipo_cliente().toString().toUpperCase());
            pstmt.setString(2, cliente.getNome().toUpperCase());
            pstmt.setInt(3, cliente.getNif());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erro, não adicionou nenhum cliente na bd");
            }
            System.out.println("\ninserir na BD\n");
            System.out.println("\n e o endereco a introduzir e: " + cliente.getEndereco());
            System.out.println("\n e o contacto a introduzir e: " + cliente.getContacto());
            adicionarContactoBD(cliente.getContacto());
            adicionarMoradaBD(cliente.getEndereco());
            pstmt.close();
            // cliente.setCod_cliente(codCliente);

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void adicionarMoradaBD(Endereco endereco) {
        String query = null;
        PreparedStatement pstmt = null;
        short indexMorada = 0;

        query = "INSERT INTO MORADA (MORADA, CIDADE, CLIENTEFK_MORADA) VALUES(?, ?, ?)";
        System.out.println("\n vai inserir: " + endereco.toString());

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, endereco.getEnderecoMorada().toUpperCase());
            pstmt.setString(2, endereco.getCidade().toUpperCase());
            pstmt.setInt(3, endereco.getNifCliente());
            System.out.println("\n antes do execute" + endereco.getEnderecoMorada() + "" + endereco.getCidade() + "" + endereco.getNifCliente());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Erro, nao adicionou nenhuma endereco na bd");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void adicionarContactoBD(Contacto contacto) {
        String query = null;
        PreparedStatement pstmt = null;
        short indexContacto = 0;

        query = "INSERT INTO CONTACTO (TELEFONE, COD_CLIENTE_FK) VALUES(?, ?)";
        System.out.println("\nvai inserir: " + contacto.toString());
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, contacto.getTelefone());
            pstmt.setInt(2, contacto.getNifCliente());
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nao recebeu nenhum codigo de cliente gerado automaticamente");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean removerCliente(int nif) {
        String query = null;
        PreparedStatement pstmt = null;
        boolean temp = false;
        //depois mudar para cliente.getCod_cliente();
        query = "DELETE FROM CLIENTE WHERE NIF =?";
        try {
            pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, nif);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows != 0) {
                temp = true;
            }
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }

        return temp;
    }

    @Override
    public boolean removerMoradasCliente(int nif) {
        String query = null;
        PreparedStatement pstmt = null;
        boolean temp = false;
        //depois mudar para cliente.getCod_cliente();
        query = "DELETE FROM MORADA WHERE CLIENTEFK_MORADA =?";
        try {
            pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, nif);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows != 0) {
                temp = true;
            }
            pstmt.close();
            //temp=true;
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("REMOVER MORADAS CLIENTE tem do remover moradas" + temp);
        return temp;
    }

    @Override
    public void removerMoradaClientePorEndereco(int nif, String endereco) {
        String query = null;
        PreparedStatement pstmt = null;
        //depois mudar para cliente.getCod_cliente();
        query = "DELETE FROM MORADA WHERE CLIENTEFK_MORADA =? AND MORADA =?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, nif);
            pstmt.setString(2, endereco.toUpperCase());
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean removerContactosCliente(int nif) {
        String query = null;
        PreparedStatement pstmt = null;
        boolean temp = false;
        query = "DELETE FROM CONTACTO WHERE COD_CLIENTE_FK =?";
        try {
            pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, nif);
            //temp = pstmt.execute();
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows != 0) {
                temp = true;
            }
            pstmt.close();
            //  temp=true;
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("REMOVER CONTACTOS CLIENTE tem do remover contactos" + temp);
        return temp;
    }

    @Override
    public void removerContactoClientePorTelefone(int nif, int telefone) {
        String query = null;
        PreparedStatement pstmt = null;
        //depois mudar para cliente.getCod_cliente();
        query = "DELETE FROM CONTACTO WHERE COD_CLIENTE_FK =? AND TELEFONE =?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, nif);
            pstmt.setInt(2, telefone);
            pstmt.execute();
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    //listar clientes
    public List<Cliente> listarClientes() {
        List<Cliente> temp = new ArrayList<>();
        System.out.println("listar clientes ");
        String query = null;
        PreparedStatement pstmt = null;
        int cod = 0;
        query = "SELECT * FROM CLIENTE";
        try {
            pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                System.out.println("" + rs.getString("tipo_cliente").toUpperCase() + "");
                cliente.setTipo_cliente(TipoDeCliente.valueOf(rs.getString("tipo_cliente").toUpperCase()));
                cliente.setNome(rs.getString("nome"));
                cliente.setNif(rs.getInt("nif"));
                cliente.setEndereco(getMorada(rs.getInt("nif")));
                cliente.setContacto(getContacto(rs.getInt("nif")));
                System.out.println("\ncliente que foi lido: "+cliente.toString());
                temp.add(cliente);
            }
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
   
    
//get morada atraves do cod_cliente
    @Override
    public Endereco getMorada(int cod_cliente) {
        Endereco m = null;
        short index_morada = 0;
        String morada = null;
        String cidade = null;
        try {
            String query = ("SELECT * FROM MORADA WHERE CLIENTEFK_MORADA=?");
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, cod_cliente);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();

            while (rs.next()) {
                index_morada = rs.getShort("index_morada");
                morada = rs.getString("morada");
                cidade = rs.getString("cidade");
            }
            pstmt.close();
            m = new Endereco(index_morada, morada, cidade,cod_cliente);
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m;
    }

    @Override
    public Contacto getContacto(int cod_cliente) {
        Contacto c = null;
        short index_contacto = 0;
        int telefone = 0;
        try {
            String query = ("SELECT * FROM CONTACTO WHERE COD_CLIENTE_FK=?");
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, cod_cliente);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();

            while (rs.next()) {
                index_contacto = rs.getShort("index_contacto");
                telefone = rs.getInt("telefone");
            }
            pstmt.close();
            c = new Contacto(index_contacto, telefone, cod_cliente);
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
//listar clientes que tem um determinado nome
    @Override
    public List<Cliente> listarClientesNome(String nome) {
        List<Cliente> temp = new ArrayList<>();
        System.out.println("listar clientes ");
        String query = null;
        PreparedStatement pstmt = null;
        query = "SELECT * FROM CLIENTE WHERE NOME=?";
        try {
            System.out.println("este é o nome que chegou: " + nome);
            pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, nome.toUpperCase());
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setTipo_cliente(TipoDeCliente.valueOf(rs.getString("tipo_cliente").toUpperCase()));
                cliente.setNome(rs.getString("nome"));
                cliente.setNif(rs.getInt("nif"));
                cliente.setEndereco(getMorada(rs.getInt("nif")));
                cliente.setContacto(getContacto(rs.getInt("nif")));
                System.out.println("\n cliente do listar clientes a sair"+cliente.toString());
                temp.add(cliente);

            }
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;
    }
//obter cliente que possui um determinado nif

    @Override
    public Cliente getClienteNif(int nif) {
        Cliente temp = null;
        String query = null;
        PreparedStatement pstmt = null;
        query = "SELECT * FROM CLIENTE WHERE NIF=?";
        try {
            pstmt = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, nif);
            pstmt.execute();
            ResultSet rs = pstmt.getResultSet();
            while (rs.next()) {
                System.out.println("o tipo de cliente e: " + rs.getString("tipo_cliente").toUpperCase());
                temp = new Cliente();
                temp.setTipo_cliente(TipoDeCliente.valueOf(rs.getString("tipo_cliente").toUpperCase()));
                temp.setNome(rs.getString("nome"));
                temp.setNif(rs.getInt("nif"));
                temp.setEndereco(getMorada(nif));
                temp.setContacto(getContacto(nif));
            }
            pstmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Queries.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp;

    }

}
