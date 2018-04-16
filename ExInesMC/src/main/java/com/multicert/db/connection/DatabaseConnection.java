/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.multicert.db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aines
 */
public class DatabaseConnection {

    Connection connection = null;
    String url = "jdbc:postgresql://localhost:5432/BDGestaoMC";
    String user = "postgres";
    String password = "cenas";

    public Connection dbConnection() {
        System.out.println("I'm here in the connection class");
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex.getClass().getName() + ":" + ex.getMessage());
            System.exit(0);
        }
        System.out.println("fez a ligação com sucesso");
        return connection;

    }

}
