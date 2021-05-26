/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.RankingDto;
import game.util.Const;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Acess Object for a ranking file.
 *
 * @author Masaomi
 */
public class RankingDao extends DBAccessor implements IRankingDao {
    
    /**
     * Read Rankng from Ranking Table
     *
     * @return List of RankingDto
     */
    @Override
    public List<RankingDto> getAll() {
        
        Product db = open();
        List<RankingDto> rankings = new ArrayList<>();

        ResultSet rs = db.executeSelect("SELECT * FROM RANKING");
        try {
            while (rs.next()) {
                RankingDto rank = new RankingDto();
                rank.setRank(rs.getInt("PLAYER_ID"));
                rank.setPlayerId(rs.getInt("PLAYER_ID"));
                rank.setName(rs.getString("PLAYER_NAME"));
                rank.setScore(rs.getInt("SCORE"));
                rankings.add(rank);
            }

            return rankings;
        } catch (SQLException ex) {
            Logger.getLogger(RankingDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            close(db);
        }

        return rankings;
    }

    /**
     * Register top10 Raning to Ranking table
     *
     * @param ranking a list of sorted RankngDto
     */
    @Override
    public void register(List<RankingDto> ranking) {

        Product db = open();
        RankingDto rankItem = null;

        //the following code might cause array index out of bounds exception when ranking.size() < Const.RANKING_RECORD_LIMIT
        //for (int i = 0; i < Const.RANKING_RECORD_LIMIT; i++);
        for (int i = 0; i < ranking.size(); i++) {
            if (i >= Const.RANKING_RECORD_LIMIT) {
                break;
            }
            rankItem = ranking.get(i);
            db.executeUpdate("INSERT INTO RANKING VALUES("
                    + rankItem.getRank() + ","
                    + rankItem.getPlayerId() + ", '"
                    + rankItem.getName() + "',"
                    + rankItem.getScore() + ")"
            );
        }
        
        close(db);
    }
}
