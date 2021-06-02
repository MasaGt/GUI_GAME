/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

/**
 * Manage DB connection.
 * @author Masaomi
 */
public class DBAccessor {
    
    /**
     * open a connection.
     * @return 
     */
    public Product open() {
        Factory factory = new DBFactory();
        Product db = factory.create();
        return db;
    }
    
    /**
     * close a connection.
     * @param db 
     */
    public void close(Product db) {
        if (db.getConn() != null) {
            db.close();
        }
    }
}
