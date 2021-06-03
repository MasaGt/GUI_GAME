/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.Player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Acess Object for a player file.
 *
 * @author Masaomi
 */
public class UserDao extends DBAccessor implements IUserDao {

    /**
     * Register a player info to Players table.
     *
     * @param player
     */
    @Override
    public void regiter(Player player) {
        Product db = open();
        db.executeUpdate("INSERT INTO PLAYERS VALUES (" + player.getId() + ", '" + player.getName() + "')");
        close(db);
    }

    /**
     * Get a new ID for a new player.
     *
     * @return a new id
     */
    @Override
    public int getNewId() {
        Product db = open();
        int id = 0;

        ResultSet rs = db.executeSelect("SELECT MAX(ID) AS ID FROM PLAYERS");
        try {
            if (rs.next()) {
                id = rs.getInt("ID");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(db);
        }

        return id + 1;
    }

    /**
     * Read the latest ID from a player table.
     *
     * @return player if it is found by id. Otherwise, null.
     */
    @Override
    public Player getById(int target) {
        Product db = open();
        Player player = null;
        int id;
        String name;

        ResultSet rs = db.executeSelect("SELECT * FROM PLAYERS WHERE ID = " + target);
        try {
            if (rs.next()) {
                player = new Player(rs.getString("NAME"), rs.getInt("ID"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(db);
        }

        return player;
    }

}
