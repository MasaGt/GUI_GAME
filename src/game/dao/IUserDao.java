/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.Player;

/**
 * The Object for writing to and reading from a player file.
 * @author Masaomi
 */
public interface IUserDao {
    
    void regiter(Player player);
    int getNewId();
    Player getById(int id);
    
}
