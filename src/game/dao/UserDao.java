/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.Player;
import game.util.Const;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Acess Object for a player file.
 * @author Masaomi
 */
public class UserDao implements IUserDao{

    private final String FILE_PATH;

    public UserDao() {
        this.FILE_PATH = "./DataStore/player.txt";
    }
    
    /**
     * Register a player info to Player File.
     * @param player 
     */
    @Override
    public void regiter(Player player) {
        
        FileOutputStream fos;
        
        try {
            fos = new FileOutputStream(FILE_PATH, true);
            PrintWriter pw = new PrintWriter(fos);
            pw.println(player.getId() + Const.DELIMITER + player.getName());
            pw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Get a new ID for a new player.
     * @return a new id
     */
    @Override
    public int getNewId() {
        BufferedReader br = null;
        int id = 0;
        String line = "";

        try {
            File file = new File(FILE_PATH);
            br = new BufferedReader(new FileReader(file));
            
            while ((line = br.readLine()) != null) {
                String[] props = line.split(Const.DELIMITER);
                id = Integer.parseInt(props[0]);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return id + 1;
    }

    /**
     * Read the latest ID from a player file.
     * @return player if it is found by id. Otherwise, null.
     */
    @Override
    public Player getById(int target) {
        BufferedReader br = null;
        String line = "";
        Player player = null;
        int id;
        String name;
        
        try {
            File file = new File(FILE_PATH);
            br = new BufferedReader(new FileReader(file));
            
            while ((line = br.readLine()) != null) {
                String[] props = line.split(Const.DELIMITER);
                 id = Integer.parseInt(props[0]);
                 name = props[1];
                 
                if (target == id) {
                    player = new Player(name, id);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return player;
    }
}
