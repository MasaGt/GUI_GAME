/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import java.sql.Connection;

/**
 *
 * @author Masaomi
 */
public class DBAccessor {
    
    public Product open() {
        Factory factory = new DBFactory();
        Product db = factory.create();
        return db;
    }
    
    public void close(Product db) {
        if (db.getConn() != null) {
            db.close();
        }
    }
}
