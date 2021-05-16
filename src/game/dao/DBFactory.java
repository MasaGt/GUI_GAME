/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

/**
 *
 * @author Masaomi
 */
public class DBFactory extends Factory{

    @Override
    protected Product getConnection() {
        return new DBProduct();
    }
    
}
