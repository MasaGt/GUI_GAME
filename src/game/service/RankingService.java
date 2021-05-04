/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.service;

import game.dao.IRankingDao;
import game.dao.RankingDao;
import game.entity.Player;
import game.entity.RankingDto;
import game.util.Const;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Business Logic about Ranking.
 * @author Masaomi
 */
public class RankingService {

    /**
     * Get all the ranking
     * @return list of rankingDto
     */
    public List<RankingDto> getRanking() {
        IRankingDao dao = new RankingDao();
        List<RankingDto> ranking = dao.getAll();
        return ranking;
    }

    //Register Ranking if player's score in the ranking.
    //Otherwise, skip registering.
    public void registerToRanking(Player player) {
        IRankingDao dao = new RankingDao();
        List<RankingDto> ranking = dao.getAll();

        boolean updateRanking = false;

        //judge if player should be registerd to ranking or not
        if (ranking.size() > Const.RANKING_RECORD_LIMIT) {
            
            //when there is more than 10 records in the ranking,
            int lowestScore = ranking.get(ranking.size() - 1).getScore();
            String lowestPlayer = ranking.get(ranking.size() - 1).getName();
            //player should be registed if player's score is higher than the lowest score in the ranking.
            if (player.getScore() > lowestScore) {
                updateRanking = true;
            } else if (player.getScore() == lowestScore && player.getName().compareTo(lowestPlayer) < 0) {
                //if payer's score is same as the llowest score, then name should be compared by lexical order.
                updateRanking = true;
            }
        } else {
            //player should be registered to ranking when there is less than 10 records.
            updateRanking = true;
        }

        if (updateRanking) {
            RankingDto newRank = new RankingDto();
            newRank.setName(player.getName());
            newRank.setPlayerId(player.getId());
            newRank.setScore(player.getScore());
            ranking.add(newRank);

            //sort by score then name
            Collections.sort(ranking, Comparator.comparing(RankingDto::getScore).reversed().thenComparing(RankingDto::getName));

            //allocat new ranking id
            for (int i = 0; i < ranking.size(); i++) {
                //Reason of i + 1 for setRank(i + 1): rank should start form 1.
                ranking.get(i).setRank(i + 1);
            }
            
            dao.register(ranking);
        }

    }

}
