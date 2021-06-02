/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Product class of Factoy pattern
 * @author Masaomi
 */
public class DBProduct extends Product {

    private static final String USER_NAME = "pdc";
    private static final String PASSWORD = "pdc";
    private static final String URL = "jdbc:derby:GameStoreDB_Ebd; create=true";
    private Connection conn;

    public DBProduct() {
        open();
    }

    /**
     * connect to the DB.
     */
    @Override
    public void open() {
        try {
            this.conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        } catch (SQLException ex) {
            Logger.getLogger(DBProduct.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * close the connection.
     */
    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Execuet a query (select).
     * @param sql
     * @return 
     */
    @Override
    public ResultSet executeSelect(String sql) {
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = this.conn.createStatement();
            resultSet = statement.executeQuery(sql);

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return resultSet;
    }

    /**
     * return DB connection.
     * @return 
     */
    @Override
    public Connection getConn() {
        return this.conn;
    }

    /**
     * execute a query (update, insert, delete).
     * @param sql 
     */
    @Override
    public void executeUpdate(String sql) {
        Statement statement = null;
        
        try {
            statement = this.conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            System.err.println(ex);
            System.err.println(ex.getMessage());
        }
    }
}
