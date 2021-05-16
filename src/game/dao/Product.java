/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author Masaomi
 */
public abstract class Product {
    
    public abstract void open();
    public abstract void close();
    public abstract ResultSet executeSelect(String sql);
    public abstract void executeUpdate(String sql);
    public abstract Connection getConn();
}
