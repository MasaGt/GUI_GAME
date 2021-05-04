/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.service;

import game.dao.IUserDao;
import game.dao.UserDao;
import game.entity.Player;

/**
 *
 * @author Masaomi
 */
public class PlayerRegisterService {
    
    public int getNewId() {
        IUserDao userDao = new UserDao();
        return userDao.getNewId();
    }
    
    public void registPlayer(Player player) {
        
        IUserDao userDao = new UserDao();
        
        //if a player is not registerd in a player file yet.
        if (userDao.getById(player.getId()) == null) {
            userDao.regiter(player);
        }
        
    }
}
