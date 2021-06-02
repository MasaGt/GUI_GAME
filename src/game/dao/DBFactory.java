/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

/**
 * Abstract Factory class of Factoy pattern.
 * @author Masaomi
 */
public class DBFactory extends Factory{

    /**
     * produce a product to connect to the DB.
     * @return 
     */
    @Override
    protected Product getConnection() {
        return new DBProduct();
    }
    
}
