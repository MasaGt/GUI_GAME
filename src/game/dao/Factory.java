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
public abstract class Factory {
    
    public final Product create() {
        Product p = this.getConnection();
        return p;
    }
    
    protected abstract Product getConnection();
    
}
