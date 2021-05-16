/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.QuizDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Acess Object for a quiz file.
 *
 * @author Masaomi
 */
public class QuizDao extends DBAccessor implements IQuizDao {

//    private Product db;
//
//    public QuizDao() {
//        Factory factory = new DBFactory();
//        this.db = factory.create();
//    }

    /**
     * Read quizes by specified level
     *
     * @param level
     * @return
     */
    @Override
    public List<QuizDto> getByLevel(int level) {

        Product db = open();
        List<QuizDto> quizes = new ArrayList<>();
        ResultSet rs = db.executeSelect("SELECT * FROM QUIZES WHERE LEVEL =" + level);

        try {
            while (rs.next()) {
                QuizDto quiz = new QuizDto();
                quiz.setId(rs.getInt("ID"));
                quiz.setStatement(rs.getString("STATEMENT"));
                quiz.setAnswer(rs.getInt("ANSWER"));
                quizes.add(quiz);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(db);
        }
        return quizes;
    }

}
