/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.OptionDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Acess Object for a option file.
 * @author Masaomi
 */
public class OptionDao extends DBAccessor implements IOptionDao {

//    private Product db;

    public OptionDao() {
//        Factory factory = new DBFactory();
//        this.db = factory.create();
    }
    
    /**
     * Get options corresponding to the passed level from OPTIONS table.
     * @param id
     * @return 
     */
    @Override
    public List<OptionDto> getById(int id) {

        Product db = open();
        
        List<OptionDto> options = new ArrayList<>();
        ResultSet rs = db.executeSelect("SELECT * FROM OPTIONS WHERE QUIZ_ID = " + id);
        try {
            while (rs.next()) {
                OptionDto option = new OptionDto();
                option.setQuizId(rs.getInt("QUIZ_ID"));
                option.setId(rs.getInt("OPTION_ID"));
                option.setStatement(rs.getString("STATEMENT"));
                options.add(option);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OptionDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(db);
        }
        
        return options;
    }

}
