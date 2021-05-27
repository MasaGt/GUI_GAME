/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.service;

import game.dao.IUserDao;
import game.dao.UserDao;
import game.entity.Player;
import java.util.Observable;

/**
 * This class is to manipulate the player table.
 * @author Masaomi
 */
public class PlayerRegisterService{

    /**
     * issue a new id for a player.
     */
    public int getNewId() {
        IUserDao userDao = new UserDao();
        int newId = userDao.getNewId();
        return newId;
    }
    
    /**
     * Register player to the players table.
     * @param player 
     */
    public void registPlayer(Player player) {

        IUserDao userDao = new UserDao();

        //if a player is not registerd in a player file yet.
        if (userDao.getById(player.getId()) == null) {
            userDao.regiter(player);
        }

    }
}
